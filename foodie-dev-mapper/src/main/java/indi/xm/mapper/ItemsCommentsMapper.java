package indi.xm.mapper;

import indi.xm.my.mapper.MyMapper;
import indi.xm.pojo.ItemsComments;
import indi.xm.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapper extends MyMapper<ItemsComments> {

    /**
     * 根据 itemId 查找商品的所有评价信息
     *
     * @param map
     * @return
     */
    public List<ItemCommentVO> queryItemComments(@Param("map") Map<String,Object> map);

    /**
     * 保存商品的评价列表
     *
     * @param map
     */
    public void saveComments(@Param("paramsMap") Map<String,Object> map);

}