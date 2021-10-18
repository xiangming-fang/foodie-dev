package indi.xm.controller.center;

import indi.xm.service.center.MyOrdersService;
import indi.xm.utils.PagedGridResult;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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
    public XMJSONResult catItems(
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
}
