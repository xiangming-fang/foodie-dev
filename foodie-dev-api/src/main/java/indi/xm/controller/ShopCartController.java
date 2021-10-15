package indi.xm.controller;

import indi.xm.bo.ShopCartBO;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller
 * @ClassName: ShopCartController
 * @Author: albert.fang
 * @Description: 购物车控制器
 * @Date: 2021/10/14 20:11
 */
@RestController
// 路由
@RequestMapping("shopcart")
@Api(value = "购物车接口controller",tags = {"购物车相关api"})
public class ShopCartController {

    @PostMapping("/add")
    @ApiOperation(value = "将cookie中的购物车信息同步购物车到后端",notes = "将cookie中的购物车信息同步购物车到后端",httpMethod = "POST")
    public XMJSONResult add(@RequestParam String userId,
                            @RequestBody ShopCartBO shopCartBO,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        if (StringUtils.isBlank(userId)){
            return XMJSONResult.errorMsg("用户id为空");
        }

        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存

        return XMJSONResult.ok();
    }

    @PostMapping("/del")
    @ApiOperation(value = "从购物车里删除商品",notes = "从购物车里删除商品",httpMethod = "POST")
    public XMJSONResult del(@RequestParam String userId,
                            @RequestParam String itemSpecId,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return XMJSONResult.errorMsg("参数不能为空");
        }

        // TODO 用户在页面删除购物车的商品数据，如果用户此时已经登录则需要删除后端购物车中的数据，达到同步的目的

        return XMJSONResult.ok();
    }
}
