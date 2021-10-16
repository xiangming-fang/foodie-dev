package indi.xm.service;

import indi.xm.pojo.Items;
import indi.xm.pojo.ItemsImg;
import indi.xm.pojo.ItemsParam;
import indi.xm.pojo.ItemsSpec;
import indi.xm.utils.PagedGridResult;
import indi.xm.vo.CommentLevelCountVO;
import indi.xm.vo.ShopCartVO;

import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service
 * @ClassName: ItemService
 * @Author: albert.fang
 * @Description: 商品
 * @Date: 2021/10/14 14:08
 */
public interface ItemService {

    /**
     * 根据商品id查询商品详情
     *
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     *
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     *
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id，查询商品参数
     *
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品id查询商品的评价等级（好评、中评、差评）数量
     *
     * @param itemId
     */
    public CommentLevelCountVO queryCommentCounts(String itemId);

    /**
     * 根据商品id和等级查询具体商品评论
     *
     * @param itemId
     * @return
     */
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 搜索商品
     *
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItems(String keywords,String sort, Integer page, Integer pageSize);

    /**
     * 根据分类id 搜索商品
     *
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItemsByThirdCat(String catId,String sort, Integer page, Integer pageSize);

    /**
     * 根据商品规格ids查找商品信息集合（用于刷新购物车中的数据）
     *
     * @param specIds
     * @return
     */
    public List<ShopCartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     *
     * @param specId
     * @return
     */
    public ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品id获取商品图片主图url
     *
     * @param itemId
     * @return
     */
    public String queryItemMainImgById(String itemId);

    /**
     * 订单创建完毕之后，记得减少库存
     *
     * @param specId
     * @param buyCounts
     */
    public void decreaseItemSpecStock(String specId,int buyCounts);
}
