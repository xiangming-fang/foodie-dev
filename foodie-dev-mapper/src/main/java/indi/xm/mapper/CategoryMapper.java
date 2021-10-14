package indi.xm.mapper;

import indi.xm.my.mapper.MyMapper;
import indi.xm.pojo.Category;
import indi.xm.vo.CategoryVO;

import java.util.List;

public interface CategoryMapper extends MyMapper<Category> {

    /**
     * 根据一级分类id自连接查询自己分类
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);
}