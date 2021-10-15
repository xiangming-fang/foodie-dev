package indi.xm.service;

import indi.xm.pojo.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service
 * @ClassName: UserAddressService
 * @Author: albert.fang
 * @Description: 用户地址
 * @Date: 2021/10/15 13:06
 */
public interface UserAddressService {

    /**
     * 通过用户id查找用户地址
     *
     * @param userId
     * @return
     */
    public List<UserAddress> queryAddressListByUserId(String userId);
}
