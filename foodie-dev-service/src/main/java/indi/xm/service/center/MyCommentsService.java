package indi.xm.service.center;

import indi.xm.pojo.OrderItems;

import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.center
 * @ClassName: MyCommentsService
 * @Author: albert.fang
 * @Description: 我的评价
 * @Date: 2021/10/18 19:38
 */
public interface MyCommentsService {

    /**
     * 根据订单id查找订单子表详情（订单的包含哪几个商品）
     *
     * @param orderId
     * @return
     */
    public List<OrderItems> queryPendingComment(String orderId);
}
