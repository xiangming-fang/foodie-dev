package indi.xm.service.center;

import indi.xm.pojo.Orders;
import indi.xm.utils.PagedGridResult;
import indi.xm.vo.OrderStatusCountsVO;

import java.util.Map;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.center
 * @ClassName: MyOrdersService
 * @Author: albert.fang
 * @Description: 订单管理
 * @Date: 2021/10/18 18:13
 */
public interface MyOrdersService {

    /**
     * 根据用户id、订单状态分页查询我的订单
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, int page, int pageSize);


    /**
     * 将订单状态改成发货状态
     *
     * @param orderId
     */
    public void updateDeliverOrderStatus(String orderId);

    /**
     * 查询我的订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    public Orders queryMyOrder(String userId,String orderId);

    /**
     * 更新订单状态 -> 确认收货
     *
     * @param orderId
     * @return
     */
    public boolean updateReceiveOrderStatus(String orderId);

    /**
     * 逻辑删除订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    public boolean deleteOrder(String userId,String orderId);

    /**
     * 查询我的订单各种状态的数量
     * orderStatus: 10、20、30、40、50
     * 以及是否评价过
     * @param userId
     * @return
     */
    public OrderStatusCountsVO getMyOrderStatusCounts(String userId);
}
