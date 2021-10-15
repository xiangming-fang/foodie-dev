package indi.xm.controller;

import indi.xm.bo.UserAddressBO;
import indi.xm.pojo.UserAddress;
import indi.xm.service.UserAddressService;
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
     * 3、删除收货地址
     * 4、修改收货地址
     * 5、设置默认地址
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
        UserAddress userAddress = userAddressService.createUserAddress(userAddressBO);
        return XMJSONResult.ok(userAddress);
    }


}
