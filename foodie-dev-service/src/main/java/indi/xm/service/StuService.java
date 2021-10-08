package indi.xm.service;

import indi.xm.pojo.Stu;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service
 * @ClassName: StuService
 * @Author: albert.fang
 * @Description: 用来复习restful webservice 接口风格
 * @Date: 2021/10/8 14:17
 */
public interface StuService {

    public Stu getStuInfo(int id);

    public void saveStu();

    public void updateStu(int id);

    public void deleteStu(int id);
}
