package indi.xm.service.center.impl;

import indi.xm.bo.center.OrderItemsCommentBO;
import indi.xm.enums.YesOrNoEnum;
import indi.xm.mapper.ItemsCommentsMapper;
import indi.xm.mapper.OrderItemsMapper;
import indi.xm.mapper.OrderStatusMapper;
import indi.xm.mapper.OrdersMapper;
import indi.xm.pojo.OrderItems;
import indi.xm.pojo.OrderStatus;
import indi.xm.pojo.Orders;
import indi.xm.service.center.MyCommentsService;
import org.n3r.idworker.Sid;
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
 * @ClassName: MyCommentsServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/18 19:39
 */
@Service
public class MyCommentsServiceImpl implements MyCommentsService {

    @Resource
    private OrderItemsMapper orderItemsMapper;

    @Resource
    private ItemsCommentsMapper itemsCommentsMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList) {

        // 1、保存评价 item_comments
        for (OrderItemsCommentBO sc : commentList) {
            sc.setCommentId(sid.nextShort());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("commentList",commentList);
        itemsCommentsMapper.saveComments(map);

        // 2、修改订单表改为已评价
        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setIsComment(YesOrNoEnum.YES.type);
        ordersMapper.updateByPrimaryKeySelective(orders);


        // 3、修改订单表的留言时间 order_status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
}
