package indi.xm.service.impl;

import indi.xm.bo.UserAddressBO;
import indi.xm.enums.AddressEnum;
import indi.xm.mapper.UserAddressMapper;
import indi.xm.pojo.UserAddress;
import indi.xm.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.impl
 * @ClassName: UserAddressServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/15 13:11
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    @Resource
    private Sid sid;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserAddress> queryAddressListByUserId(String userId) {

        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);

        return userAddressMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserAddress createUserAddress(UserAddressBO userAddressBO) {
        String addressId = sid.nextShort();
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userAddressBO.getUserId());
        userAddress.setCity(userAddressBO.getCity());
        userAddress.setDetail(userAddressBO.getDetail());
        userAddress.setDistrict(userAddressBO.getDistrict());
        userAddress.setReceiver(userAddressBO.getReceiver());
        userAddress.setMobile(userAddressBO.getMobile());
        userAddress.setProvince(userAddressBO.getProvince());
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddress.setIsDefault(AddressEnum.NO.type);
        userAddressMapper.insert(userAddress);
        return userAddress;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserAddress setDefaultAddress(String userId, String addressId) {
        // 1、如果之前存在默认地址，那么找到之前的那个默认地址，将其设为非默认地址
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("isDefault",AddressEnum.YES.type);
        UserAddress userAddress = userAddressMapper.selectOneByExample(example);
        if (userAddress != null){
            userAddress.setIsDefault(AddressEnum.NO.type);
            userAddressMapper.updateByPrimaryKey(userAddress);
        }
        // 2、将目标addressId的记录设为默认地址
        UserAddress targetAddress = userAddressMapper.selectByPrimaryKey(addressId);
        targetAddress.setIsDefault(AddressEnum.YES.type);
        userAddressMapper.updateByPrimaryKey(targetAddress);
        return targetAddress;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        userAddressMapper.delete(userAddress);
    }
}
