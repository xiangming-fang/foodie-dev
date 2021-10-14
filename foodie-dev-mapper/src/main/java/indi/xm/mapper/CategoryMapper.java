package indi.xm.mapper;

import indi.xm.my.mapper.MyMapper;
import indi.xm.pojo.Category;
import indi.xm.vo.CategoryVO;
import indi.xm.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends MyMapper<Category> {

    /**
     * 根据一级分类id自连接查询自己分类
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     *
     * @param map
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String,Object> map);
}