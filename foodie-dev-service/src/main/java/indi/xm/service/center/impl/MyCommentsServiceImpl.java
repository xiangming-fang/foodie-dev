package indi.xm.service.center.impl;

import indi.xm.mapper.OrderItemsMapper;
import indi.xm.pojo.OrderItems;
import indi.xm.service.center.MyCommentsService;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }
}
