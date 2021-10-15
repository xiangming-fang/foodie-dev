package indi.xm.controller;

import indi.xm.bo.SubmitOrderBO;
import indi.xm.bo.UserAddressBO;
import indi.xm.enums.PayMethodEnum;
import indi.xm.pojo.UserAddress;
import indi.xm.service.UserAddressService;
import indi.xm.utils.MobileEmailUtils;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller
 * @ClassName: AddressController
 * @Author: albert.fang
 * @Description: 订单相关
 * @Date: 2021/10/15 11:35
 */
@Api(value = "订单相关",tags = {"订单相关的api接口"})
@RestController
@RequestMapping("orders")
public class OrdersController {

    @Resource
    private UserAddressService userAddressService;

    @PostMapping("/create")
    @ApiOperation(value = "用户下单",notes = "用户下单",httpMethod = "POST")
    public XMJSONResult create(@RequestBody SubmitOrderBO submitOrderBO){

        if (!Objects.equals(submitOrderBO.getPayMethod(), PayMethodEnum.ALIPAY.type) && !Objects.equals(submitOrderBO.getPayMethod(), PayMethodEnum.WEIXIN.type)){
            return XMJSONResult.errorMsg("支付方式错误");
        }

        System.out.println(submitOrderBO.toString());

        // 1、创建订单
        // 2、创建订单以后，移除购物车中已结算（已提交）的商品
        // 3、向支付中心发送当前订单，用于保存支付中心的订单数据
        return XMJSONResult.ok();
    }

}
