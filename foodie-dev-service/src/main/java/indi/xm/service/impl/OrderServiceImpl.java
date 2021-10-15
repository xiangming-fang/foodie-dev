package indi.xm.service.impl;

import indi.xm.bo.SubmitOrderBO;
import indi.xm.enums.YesOrNoEnum;
import indi.xm.mapper.UserAddressMapper;
import indi.xm.pojo.Orders;
import indi.xm.pojo.UserAddress;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder(SubmitOrderBO submitOrderBO) {

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
        Orders newOrder = new Orders();
        newOrder.setId(sid.nextShort());
        newOrder.setUserId(userId);
        newOrder.setReceiverName(userAddress.getReceiver());
        newOrder.setReceiverMobile(userAddress.getMobile());
        newOrder.setReceiverAddress(userAddress.getProvince() + " " +
                                    userAddress.getCity() + " " +
                                    userAddress.getDistrict() + " " +
                                    userAddress.getDetail());
//        newOrder.setTotalAmount();
//        newOrder.setRealPayAmount();
        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNoEnum.NO.type);
        newOrder.setIsDelete(YesOrNoEnum.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2、循环根据itemSpecIds保存订单商品信息表

        // 3、保存订单状态表


    }
}
