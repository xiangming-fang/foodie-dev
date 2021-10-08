package indi.xm.service.impl;

import indi.xm.mapper.StuMapper;
import indi.xm.pojo.Stu;
import indi.xm.service.StuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.impl
 * @ClassName: StuServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/8 14:20
 */
@Service
public class StuServiceImpl implements StuService {

    @Resource
    private StuMapper stuMapper;

    // TODO 为啥这里的事务用 propagation.supports 就可以
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    // TODO 为啥这里的事务用 propagation.required 就可以
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu() {
        Stu stu = new Stu();
        stu.setAge(12);
        stu.setName("方翔鸣");
        stuMapper.insertUseGeneratedKeys(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setAge(16);
        stu.setName("方鹏飞");
        stuMapper.updateByPrimaryKey(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }
}
