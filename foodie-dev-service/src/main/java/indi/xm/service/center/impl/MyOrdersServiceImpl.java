package indi.xm.service.center.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xm.enums.OrderStatusEnum;
import indi.xm.enums.YesOrNoEnum;
import indi.xm.mapper.OrderStatusMapper;
import indi.xm.mapper.OrdersMapper;
import indi.xm.pojo.OrderStatus;
import indi.xm.pojo.Orders;
import indi.xm.service.center.MyOrdersService;
import indi.xm.utils.PagedGridResult;
import indi.xm.vo.MyOrdersVO;
import indi.xm.vo.OrderStatusCountsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.center.impl
 * @ClassName: MyOrdersServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/18 18:16
 */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, int page, int pageSize) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        if (orderStatus != null){
            map.put("orderStatus",orderStatus);
        }

        // 开始分页
        PageHelper.startPage(page,pageSize);

        List<MyOrdersVO> list = ordersMapper.queryMyOrders(map);

        return setterPageGrid(list,page);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        orderStatus.setDeliverTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Orders queryMyOrder(String userId, String orderId) {
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",orderId);
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("isDelete", YesOrNoEnum.NO.type);
        return ordersMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateReceiveOrderStatus(String orderId) {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        orderStatus.setSuccessTime(new Date());

        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",orderId);
        criteria.andEqualTo("orderStatus",OrderStatusEnum.WAIT_RECEIVE.type);

        // 要完成收货的前面一个状态必须是待收货才行
        // wait receive -> receive
        int res = orderStatusMapper.updateByExampleSelective(orderStatus, example);

        return res == 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteOrder(String userId, String orderId) {

        Orders updateOrder = new Orders();
        updateOrder.setIsDelete(YesOrNoEnum.YES.type);
        updateOrder.setUpdatedTime(new Date());

        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",orderId);
        criteria.andEqualTo("userId",userId);

        int res = ordersMapper.updateByExampleSelective(updateOrder, example);
        return res == 1;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public OrderStatusCountsVO getMyOrderStatusCounts(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("orderStatus",OrderStatusEnum.WAIT_PAY.type);

        // 1、待付款数量
        int waitPayCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        // 2、已付款，待发货
        map.put("orderStatus",OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliverCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        // 3、已发货，待收货
        map.put("orderStatus",OrderStatusEnum.WAIT_RECEIVE.type);
        int waitReceiveCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        // 4、交易成功 & 待评价
        map.put("orderStatus",OrderStatusEnum.SUCCESS.type);
        map.put("isComment",YesOrNoEnum.NO.type);
        int waitCommentCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        OrderStatusCountsVO orderStatusCountsVO = new OrderStatusCountsVO(waitPayCounts, waitDeliverCounts, waitReceiveCounts, waitCommentCounts);

        return orderStatusCountsVO;
    }

    private PagedGridResult setterPageGrid(List<?> list, Integer page){
        // 分页处理
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult gridResult = new PagedGridResult();
        // 当前第几页
        gridResult.setPage(page);
        // list 分页后的数据
        gridResult.setRows(list);
        // total 总页数
        gridResult.setTotal(pageList.getPages());
        // records 总记录数
        gridResult.setRecords(pageList.getTotal());
        return gridResult;
    }
}
