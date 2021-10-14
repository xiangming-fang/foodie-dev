package indi.xm.controller;

import indi.xm.bo.UserBo;
import indi.xm.pojo.Users;
import indi.xm.service.UserService;
import indi.xm.utils.CookieUtils;
import indi.xm.utils.JsonUtils;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller
 * @ClassName: PassPortController
 * @Author: albert.fang
 * @Description: 通行证
 * @Date: 2021/10/11 14:01
 */
@RestController
// 路由
@RequestMapping("passport")
@Api(value = "注册登录",tags = {"用于注册登录的相关接口"})
public class PassPortController {

    @Resource
    public UserService userService;

    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    @GetMapping("/usernameIsExist")
    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    // @RequestParam 代表是一种请求类型的参数，而不是一种路径参数
    public int usernameIsExist(@RequestParam String username){

        // 1、判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return 500;
        }

        // 2、查找注册的用户名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist){
            return 500;
        }

        // 3、请求成功，用户名没有重复
        return 200;
    }

    /**
     * 用户注册
     *
     * @param userbo
     * @return
     */
    @PostMapping("/register")
    // @RequestBody 代表参数是一个对象
    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    public XMJSONResult register(@RequestBody UserBo userbo,
                                 HttpServletRequest request, HttpServletResponse response){
        String username = userbo.getUsername();
        String password = userbo.getPassword();
        String confirmPassword = userbo.getConfirmPassword();

        // 1、判断用户名密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return XMJSONResult.errorMsg("用户名或密码为空");
        }
        // 2、查询用户名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist){
            return XMJSONResult.errorMsg("用户名存在");
        }
        // 3、密码长度不能少于6位
        if (password.length() < 6 ){
            return XMJSONResult.errorMsg("密码长度不能小于6");
        }
        // 4、判断两次密码是否一致
        if (!password.equals(confirmPassword)) {
            return XMJSONResult.errorMsg("两次密码输入不一致");
        }
        // 5、实现注册
        Users user = userService.createUser(userbo);
        // 将一些属性设置成空，保护隐私
        setNullProperty(user);
        // 设置cookie信息
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(user),true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return XMJSONResult.ok();
    }

    /**
     * 用户登录
     *
     * @param userbo
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    public XMJSONResult login(@RequestBody UserBo userbo,
                              HttpServletRequest request, HttpServletResponse response){
        String username = userbo.getUsername();
        String password = userbo.getPassword();

        // 1、判断用户名密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return XMJSONResult.errorMsg("用户名或密码为空");
        }

        // 2、实现登录
        Users users = userService.queryUserForLogin(username, password);
        if (users == null){
            return XMJSONResult.errorMsg("用户名或密码不正确");
        }
        // 将一些属性设置成空，保护隐私
        setNullProperty(users);
        // 设置cookie信息
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(users),true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return XMJSONResult.ok(users);
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "退出登录",notes = "退出登录",httpMethod = "POST")
    public XMJSONResult logout(String userId,
                              HttpServletRequest request, HttpServletResponse response){
        // 清楚用户相关信息的cookie
        CookieUtils.deleteCookie(request,response,"user");

        // TODO 用户退出登录，需要清空购物车
        // TODO 分布式会话中需要清除用户数据

        return XMJSONResult.ok();
    }

    private void setNullProperty(Users users){
        users.setPassword(null);
        users.setRealname(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
    }
}
