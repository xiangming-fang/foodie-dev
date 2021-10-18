package indi.xm.mapper;

import indi.xm.my.mapper.MyMapper;
import indi.xm.pojo.Orders;
import indi.xm.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends MyMapper<Orders> {

    public List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map map);
}