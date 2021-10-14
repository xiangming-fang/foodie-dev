package indi.xm.mapper;

import indi.xm.my.mapper.MyMapper;
import indi.xm.pojo.Items;
import indi.xm.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapper extends MyMapper<Items> {

    /**
     * 搜索商品
     *
     * @param map
     */
    public List<SearchItemsVO> searchItems(@Param("map") Map<String,Object> map);
}