package indi.xm.controller.center;

import indi.xm.pojo.Users;
import indi.xm.service.center.CenterUserService;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller.center
 * @ClassName: CenterController
 * @Author: albert.fang
 * @Description: 用户中心控制器
 * @Date: 2021/10/18 12:54
 */
@RestController
@Api(value = "用户中心控制器",tags = "用户中心相关api")
@RequestMapping("center")
public class CenterController {

    @Resource
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "GET")
    @GetMapping("userInfo")
    public XMJSONResult userInfo(@ApiParam(name = "userId",value = "用户id",required = true) @RequestParam String userId){
        if (StringUtils.isBlank(userId)){
            return XMJSONResult.errorMsg("参数非法");
        }
        Users users = centerUserService.queryUserInfoById(userId);
        return XMJSONResult.ok(users);
    }
}
