package indi.xm.controller;

import indi.xm.bo.UserAddressBO;
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

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller
 * @ClassName: AddressController
 * @Author: albert.fang
 * @Description: 收货地址
 * @Date: 2021/10/15 11:35
 */
@Api(value = "地址相关",tags = {"地址相关的api接口"})
@RestController
@RequestMapping("address")
public class AddressController {

    @Resource
    private UserAddressService userAddressService;

    /**
     * 用户在确认订单页面，可以针对收货地址做如下操作：
     * 1、查询用户的所有收货地址列表 list
     * 2、新增收货地址 add
     * 3、删除收货地址 delete
     * 4、修改收货地址 update
     * 5、设置默认地址 setDefalut
     */


    @PostMapping("/list")
    @ApiOperation(value = "用户下的收货地址列表",notes = "用户下的收货地址列表",httpMethod = "POST")
    public XMJSONResult list(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId){
        if (StringUtils.isBlank(userId)){
            return XMJSONResult.errorMsg("用户id为空");
        }
        List<UserAddress> result = userAddressService.queryAddressListByUserId(userId);
        return XMJSONResult.ok(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "给当前用户添加收货地址",notes = "给当前用户添加收货地址",httpMethod = "POST")
    public XMJSONResult add(@RequestBody UserAddressBO userAddressBO){
        XMJSONResult checkResult = checkAddress(userAddressBO);
        if (checkResult.getStatus() != 200){
            return checkResult;
        }
        UserAddress userAddress = userAddressService.createUserAddress(userAddressBO);
        return XMJSONResult.ok(userAddress);
    }

    @PostMapping("/setDefalut")
    @ApiOperation(value = "给当前用户当前地址设为默认地址",notes = "给当前用户当前地址设为默认地址",httpMethod = "POST")
    public XMJSONResult setDefalut(@ApiParam(name = "userId",value = "用户id",required = true)
                                   @RequestParam String userId,
                                   @ApiParam(name = "addressId",value = "地址id",required = true)
                                   @RequestParam String addressId){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return XMJSONResult.errorMsg("参数非法");
        }
        UserAddress res = userAddressService.setDefaultAddress(userId, addressId);
        return XMJSONResult.ok(res);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除当前收货地址",notes = "删除当前收货地址",httpMethod = "POST")
    public XMJSONResult delete(@ApiParam(name = "userId",value = "用户id",required = true)
                                   @RequestParam String userId,
                                   @ApiParam(name = "addressId",value = "地址id",required = true)
                                   @RequestParam String addressId){
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return XMJSONResult.errorMsg("参数非法");
        }
        userAddressService.delAddress(userId, addressId);
        return XMJSONResult.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "删除当前收货地址",notes = "删除当前收货地址",httpMethod = "POST")
    public XMJSONResult update(@RequestBody UserAddressBO userAddressBO){
        XMJSONResult checkResult = checkAddress(userAddressBO);
        if (checkResult.getStatus() != 200){
            return checkResult;
        }
        UserAddress userAddress = userAddressService.updateAddress(userAddressBO);
        return XMJSONResult.ok(userAddress);
    }

    private XMJSONResult checkAddress(UserAddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return XMJSONResult.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return XMJSONResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return XMJSONResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return XMJSONResult.errorMsg("收货人手机号长度不正确");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return XMJSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return XMJSONResult.errorMsg("收货地址信息不能为空");
        }

        return XMJSONResult.ok();
    }


}
