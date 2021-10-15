package indi.xm.service.impl;

import indi.xm.mapper.UserAddressMapper;
import indi.xm.pojo.UserAddress;
import indi.xm.service.UserAddressService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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

    @Override
    public List<UserAddress> queryAddressListByUserId(String userId) {

        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);

        return userAddressMapper.selectByExample(example);
    }
}
