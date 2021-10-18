package indi.xm.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.bo
 * @ClassName: CenterUserBO
 * @Author: albert.fang
 * @Description: 个人中心的修改个人信息BO
 * @Date: 2021/10/18 13:09
 */
@ApiModel(value = "个人中心用户对象Bo",description = "从客户端，由用户传入的数据封装在此entity中")
public class CenterUserBO {

    @ApiModelProperty(name = "birthday",value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "创建日期")
    private Date createdTime;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String face;

    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$",message = "手机号格式不正确")
    private String mobile;

    @NotBlank(message = "用户名称不能为空")
    @Length(max = 12,message = "用户昵称不能超过12位")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

    @Length(max = 12,message = "用户真实姓名不能超过12位")
    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @Min(value = 0,message = "性别选择不正确")
    @Max(value = 2,message = "性别选择不正确")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "更新日期")
    private Date updatedTime;

    @ApiModelProperty(value = "用户名")
    private String username;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
