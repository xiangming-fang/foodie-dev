package indi.xm.controller.center;

import indi.xm.pojo.Orders;
import indi.xm.service.center.MyOrdersService;
import indi.xm.utils.PagedGridResult;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller.center
 * @ClassName: MyOrdersController
 * @Author: albert.fang
 * @Description: 我的订单控制器
 * @Date: 2021/10/18 17:59
 */
@RestController
@Api(value = "用户中心的订单管理",tags = "用户中心的订单管理api")
@RequestMapping("myorders")
public class MyOrdersController {

    @Resource
    private MyOrdersService myOrdersService;

    @PostMapping("/query")
    @ApiOperation(value = "查询订单列表",notes = "查询订单列表接口",httpMethod = "POST")
    public XMJSONResult query(
            @ApiParam(name = "userId",value = "userId",required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus",value = "订单状态")
            @RequestParam(required = false) Integer orderStatus,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam(required = false,defaultValue = "1") Integer page,
            @ApiParam(name = "pageSize",value = "一页大小")
            @RequestParam(required = false,defaultValue = "20") Integer pageSize){
        if (StringUtils.isBlank(userId)){
            return XMJSONResult.errorMsg("非法参数");
        }
        PagedGridResult list = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);
        return XMJSONResult.ok(list);
    }

    // 订单发货，用于模拟
    @PostMapping("/delivery")
    @ApiOperation(value = "订单发货",notes = "订单发货",httpMethod = "POST")
    public XMJSONResult delivery(
            @ApiParam(name = "orderId",value = "orderId",required = true)
            @RequestParam String orderId){
        if (StringUtils.isBlank(orderId)){
            return XMJSONResult.errorMsg("参数非法");
        }
        myOrdersService.updateDeliverOrderStatus(orderId);
        return XMJSONResult.ok();
    }

    @PostMapping("/confirmReceive")
    @ApiOperation(value = "确认收货",notes = "确认收货",httpMethod = "POST")
    public XMJSONResult confirmReceive(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId",value = "订单id",required = true)
            @RequestParam String orderId){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(orderId)){
            return XMJSONResult.errorMsg("参数非法");
        }
        XMJSONResult result = checkUserOrder(userId, orderId);

        if (result.getStatus() != HttpStatus.OK.value()) {
            return result;
        }
        boolean res = myOrdersService.updateReceiveOrderStatus(orderId);
        if (!res){
            XMJSONResult.errorMsg("订单确认收货失败");
        }
        return XMJSONResult.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "用户删除订单",notes = "用户删除订单",httpMethod = "POST")
    public XMJSONResult delete(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId",value = "订单id",required = true)
            @RequestParam String orderId){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(orderId)){
            return XMJSONResult.errorMsg("参数非法");
        }
        XMJSONResult result = checkUserOrder(userId, orderId);

        if (result.getStatus() != HttpStatus.OK.value()) {
            return result;
        }
        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if (!res){
            return XMJSONResult.errorMsg("订单删除失败");
        }
        return XMJSONResult.ok();
    }

    /**
     * 用于验证用户和订单是否有关联关系，避免非法调用
     *
     * @param userId
     * @param orderId
     * @return
     */
    private XMJSONResult checkUserOrder(String userId,String orderId){
        Orders orders = myOrdersService.queryMyOrder(userId, orderId);
        if (orders == null){
            return XMJSONResult.errorMsg("订单不存在");
        }
        return XMJSONResult.ok();
    }
}
