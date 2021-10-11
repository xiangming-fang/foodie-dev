package indi.xm.bo;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.bo
 * @ClassName: UserBo
 * @Author: albert.fang
 * @Description: 前端传给后端的实体参数
 * @Date: 2021/10/11 14:15
 */
public class UserBo {

    private String username;

    private String password;

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
