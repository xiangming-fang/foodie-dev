package indi.xm.controller.center;

import indi.xm.bo.center.CenterUserBO;
import indi.xm.pojo.Users;
import indi.xm.service.center.CenterUserService;
import indi.xm.utils.CookieUtils;
import indi.xm.utils.JsonUtils;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller.center
 * @ClassName: UserCenterController
 * @Author: albert.fang
 * @Description: 用户中心的个人信息
 * @Date: 2021/10/18 13:05
 */
@RestController
@Api(value = "用户中心的个人信息",tags = "用户中心的个人信息api")
@RequestMapping("userInfo")
public class UserCenterController {

    @Resource
    private CenterUserService centerUserService;

    /**
     * 根据userId更新用户信息
     *
     * @param userId
     * @return
     */
    @PostMapping("update")
    @ApiOperation(value = "更新用户信息",tags = "更新用户信息",httpMethod = "POST")
    public XMJSONResult update(@ApiParam(name = "userId",value = "用户id",required = true) @RequestParam String userId,
                               @RequestBody @Valid CenterUserBO centerUserBO,
                               BindingResult result,
                               HttpServletRequest request,
                               HttpServletResponse response){
        // 判断binddingresult是否包含错误的验证消息，如果有直接return
        if (result.hasErrors()) {
            Map<String, String> errors = getErrors(result);
            return XMJSONResult.errorMap(errors);
        }
        Users resUser = centerUserService.updateUserById(userId, centerUserBO);
        setNullProperty(resUser);
        // 刷新前端cookie
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(resUser),true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话

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

    /**
     * 通过hibernate的valid验证从前端传到后端的bo错误信息
     * @param bindResult
     * @return
     */
    private Map<String,String> getErrors(BindingResult bindResult){
        HashMap<String, String> errorInfoMap = new HashMap<>();
        List<FieldError> fieldErrors = bindResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            // 发生错误所对应的某一个属性
            String field = fieldError.getField();
            // 验证错误的信息
            String errorMsg = fieldError.getDefaultMessage();
            errorInfoMap.put(field,errorMsg);
        }
        return errorInfoMap;
    }
}
