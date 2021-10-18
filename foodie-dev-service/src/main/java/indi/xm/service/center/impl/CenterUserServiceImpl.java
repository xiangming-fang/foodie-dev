package indi.xm.service.center.impl;

import indi.xm.mapper.UsersMapper;
import indi.xm.pojo.Users;
import indi.xm.service.center.CenterUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.center.impl
 * @ClassName: CenterUserServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/18 12:57
 */
@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Resource
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfoById(String userId) {
        Users res = usersMapper.selectByPrimaryKey(userId);
        res.setPassword("");
        return res;
    }
}
