package indi.xm.controller.center;

import indi.xm.bo.center.OrderItemsCommentBO;
import indi.xm.enums.YesOrNoEnum;
import indi.xm.pojo.OrderItems;
import indi.xm.pojo.Orders;
import indi.xm.service.center.MyCommentsService;
import indi.xm.service.center.MyOrdersService;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller.center
 * @ClassName: MyCommentsController
 * @Author: albert.fang
 * @Description: 评论控制器
 * @Date: 2021/10/18 19:30
 */
@RestController
@Api(value = "我的评价控制器",tags = "我的评价控制器api")
@RequestMapping("mycomments")
public class MyCommentsController {

    @Resource
    private MyCommentsService myCommentsService;

    @Resource
    private MyOrdersService myOrdersService;

    @PostMapping("/pending")
    @ApiOperation(value = "查询订单待评价商品",notes = "查询订单待评价商品",httpMethod = "POST")
    public XMJSONResult pending(
            @ApiParam(name = "userId",value = "userId",required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId",value = "订单id",required = true)
            @RequestParam(required = false) String orderId){

        // 1、判断用户和订单是否关联
        XMJSONResult result = checkUserOrder(userId, orderId);
        if (result.getStatus() != HttpStatus.OK.value()){
            return XMJSONResult.errorMsg("订单非法");
        }
        // 2、判断订单是否评价过了
        Orders myOrder = (Orders)result.getData();
        if (Objects.equals(myOrder.getIsComment(), YesOrNoEnum.YES.type)) {
            return XMJSONResult.errorMsg("该订单已经评价过了");
        }

        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);
        return XMJSONResult.ok(list);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "保存评价列表",notes = "保存评价列表",httpMethod = "POST")
    public XMJSONResult saveList(
            @ApiParam(name = "userId",value = "userId",required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId",value = "订单id",required = true)
            @RequestParam(required = false) String orderId,
            @RequestBody List<OrderItemsCommentBO> commentList){

        // 1、判断用户和订单是否关联
        XMJSONResult result = checkUserOrder(userId, orderId);
        if (result.getStatus() != HttpStatus.OK.value()){
            return XMJSONResult.errorMsg("订单非法");
        }

        // 2、判断评论内容list不能为空
        if (CollectionUtils.isEmpty(commentList)){
            return XMJSONResult.errorMsg("评论内容不能为空");
        }

        myCommentsService.saveComments(orderId,userId,commentList);

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
        return XMJSONResult.ok(orders);
    }
}
