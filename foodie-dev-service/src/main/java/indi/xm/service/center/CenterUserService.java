package indi.xm.service.center;

import indi.xm.pojo.Users;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.center
 * @ClassName: CenterUserService
 * @Author: albert.fang
 * @Description: 用户中心userservice
 * @Date: 2021/10/18 12:56
 */
public interface CenterUserService {


    /**
     * 根据用户userid查询用户信息
     * @param userId
     * @return
     */
    public Users queryUserInfoById(String userId);
}
