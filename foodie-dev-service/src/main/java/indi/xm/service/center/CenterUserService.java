package indi.xm.service.center;

import indi.xm.bo.center.CenterUserBO;
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

    /**
     * 根据userid，更新用户信息
     * @param userId
     */
    public Users updateUserById(String userId, CenterUserBO centerUserBO);

    /**
     * 更新用户的头像url
     * @param userId
     * @param faceUrl
     * @return
     */
    public Users updateUserFace(String userId,String faceUrl);
}
