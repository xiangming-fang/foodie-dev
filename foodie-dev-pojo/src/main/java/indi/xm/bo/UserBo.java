package indi.xm.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.bo
 * @ClassName: UserBo
 * @Author: albert.fang
 * @Description: 前端传给后端的实体参数（和业务打交道的实体类,前端发送请求过来的）
 * @Date: 2021/10/11 14:15
 */
@ApiModel(value = "用户对象Bo",description = "从客户端，由用户传入的数据封装在此entity中")
public class UserBo {

    @ApiModelProperty(value = "用户名",name = "username",example = "admin",required = true)
    private String username;

    @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
    private String password;

    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "123456",required = false)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
