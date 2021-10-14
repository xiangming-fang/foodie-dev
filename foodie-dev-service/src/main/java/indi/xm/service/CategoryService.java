package indi.xm.service;

import indi.xm.pojo.Category;

import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service
 * @ClassName: CategoryService
 * @Author: albert.fang
 * @Description: 首页分类
 * @Date: 2021/10/14 10:44
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     *
     * @return
     */
    public List<Category> queryAllRootLevelCats();
}
