package indi.xm.service.center.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import indi.xm.bo.center.CenterUserBO;
import indi.xm.mapper.UsersMapper;
import indi.xm.pojo.Users;
import indi.xm.service.center.CenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserById(String userId, CenterUserBO centerUserBO) {
        Users updateUsers = new Users();
        BeanUtils.copyProperties(centerUserBO,updateUsers);
        updateUsers.setId(userId);
        updateUsers.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKey(updateUsers);

        return queryUserInfoById(userId);
    }
}
