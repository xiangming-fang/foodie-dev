package indi.xm.service.impl;

import indi.xm.enums.CategoryLevel;
import indi.xm.mapper.CategoryMapper;
import indi.xm.pojo.Category;
import indi.xm.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.impl
 * @ClassName: CategoryServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/14 10:45
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAllRootLevelCats() {

        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", CategoryLevel.FIRST.level);

        return categoryMapper.selectByExample(example);
    }
}
