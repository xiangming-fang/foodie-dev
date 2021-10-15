package indi.xm.service;

import indi.xm.bo.UserAddressBO;
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

    /**
     * 创建用户收货地址
     *
     * @param userAddressBO
     * @return
     */
    public UserAddress createUserAddress(UserAddressBO userAddressBO);

    /**
     * 将用户下的某个地址设为默认地址
     *
     * @param userId
     * @param addressId
     * @return
     */
    public UserAddress setDefaultAddress(String userId,String addressId);
}
