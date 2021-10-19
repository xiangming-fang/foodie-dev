package indi.xm.service.impl;

import indi.xm.bo.SubmitOrderBO;
import indi.xm.enums.OrderStatusEnum;
import indi.xm.enums.YesOrNoEnum;
import indi.xm.mapper.OrderItemsMapper;
import indi.xm.mapper.OrderStatusMapper;
import indi.xm.mapper.OrdersMapper;
import indi.xm.mapper.UserAddressMapper;
import indi.xm.pojo.*;
import indi.xm.service.ItemService;
import indi.xm.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.impl
 * @ClassName: OrderServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/15 16:18
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private Sid sid;

    @Resource
    private UserAddressMapper addressMapper;

    @Resource
    private ItemService itemService;

    @Resource
    private OrderItemsMapper orderItemsMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String createOrder(SubmitOrderBO submitOrderBO) {

        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();

        // 包邮设置为0
        Integer postAmount = 0;

        // 根据addressId得到收件信息
        UserAddress userAddress = addressMapper.selectByPrimaryKey(addressId);

        // 1、新订单数据保存
        String orderId = sid.nextShort();
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        newOrder.setReceiverName(userAddress.getReceiver());
        newOrder.setReceiverMobile(userAddress.getMobile());
        newOrder.setReceiverAddress(userAddress.getProvince() + " " +
                                    userAddress.getCity() + " " +
                                    userAddress.getDistrict() + " " +
                                    userAddress.getDetail());
        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNoEnum.NO.type);
        newOrder.setIsDelete(YesOrNoEnum.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2、循环根据itemSpecIds保存订单商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        int totalAmount = 0; // 商品原价累计
        int realPayAmount = 0; // 优惠后的实际支付价格累计

        for (String itemSpecId : itemSpecIdArr) {

            // 2.1 根据规格id，查询规格的具体信息，主要获取价格
            ItemsSpec itemsSpec = itemService.queryItemSpecById(itemSpecId);
            // TODO 整合redis后，商品购买的数量重新从redis的购物车获取
            int buyCounts = 1;
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCounts;

            // 2.2 根据商品id，获得商品信息和商品图片
            String itemId = itemsSpec.getItemId();
            Items items = itemService.queryItemById(itemId);
            String imgUrl = itemService.queryItemMainImgById(itemId);

            // 2.3 循环保存子订单数据
            OrderItems orderItems = new OrderItems();
            orderItems.setId(sid.nextShort());
            orderItems.setOrderId(orderId);
            orderItems.setItemId(itemId);
            orderItems.setItemName(items.getItemName());
            orderItems.setItemImg(imgUrl);
            orderItems.setBuyCounts(buyCounts);
            orderItems.setItemSpecId(itemSpecId);
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(orderItems);

            // 2.4 用户订单创建完毕之后，记得减库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);

        ordersMapper.insert(newOrder);

        // 3、保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);

        return orderId;
    }
}
