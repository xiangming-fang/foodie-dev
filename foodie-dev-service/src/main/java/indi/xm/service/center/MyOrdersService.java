package indi.xm.service.center;

import indi.xm.utils.PagedGridResult;
import indi.xm.vo.MyOrdersVO;

import java.util.List;

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
}
