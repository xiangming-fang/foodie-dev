package indi.xm.service.center.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xm.enums.OrderStatusEnum;
import indi.xm.mapper.OrderStatusMapper;
import indi.xm.mapper.OrdersMapper;
import indi.xm.pojo.OrderStatus;
import indi.xm.service.center.MyOrdersService;
import indi.xm.utils.PagedGridResult;
import indi.xm.vo.MyOrdersVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
