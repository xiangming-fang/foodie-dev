package indi.xm.service;

import indi.xm.bo.SubmitOrderBO;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service
 * @ClassName: OrderService
 * @Author: albert.fang
 * @Description: 订单
 * @Date: 2021/10/15 16:17
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param submitOrderBO
     */
    public String createOrder(SubmitOrderBO submitOrderBO);

}
