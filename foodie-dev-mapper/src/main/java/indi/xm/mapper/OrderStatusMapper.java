package indi.xm.mapper;

import indi.xm.my.mapper.MyMapper;
import indi.xm.pojo.OrderStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrderStatusMapper extends MyMapper<OrderStatus> {

    /**
     * 查询我的订单各种状态的数量
     * orderStatus: 10、20、30、40、50
     * 以及是否评价过
     * @param map
     * @return
     */
    public int getMyOrderStatusCounts(@Param("paramsMap") Map map);
}