package indi.xm.service;

import indi.xm.bo.UserBo;
import indi.xm.pojo.Users;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service
 * @ClassName: UserService
 * @Author: albert.fang
 * @Description: 用户
 * @Date: 2021/10/11 13:50
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUserNameIsExist(String username);

    /**
     * 前端传给后端参数统一定为XXXBo
     * 创建用户
     * @param userBo
     * @return
     */
    public Users createUser(UserBo userBo);
}
