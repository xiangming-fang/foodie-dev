package indi.xm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xm.enums.CommentLevel;
import indi.xm.mapper.*;
import indi.xm.pojo.*;
import indi.xm.service.ItemService;
import indi.xm.utils.DesensitizationUtil;
import indi.xm.utils.PagedGridResult;
import indi.xm.vo.CommentLevelCountVO;
import indi.xm.vo.ItemCommentVO;
import indi.xm.vo.SearchItemsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service.impl
 * @ClassName: ItemServiceImpl
 * @Author: albert.fang
 * @Description:
 * @Date: 2021/10/14 14:09
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemsMapper itemsMapper;

    @Resource
    private ItemsImgMapper itemsImgMapper;

    @Resource
    private ItemsSpecMapper itemsSpecMapper;

    @Resource
    private ItemsParamMapper itemsParamMapper;

    @Resource
    private ItemsCommentsMapper itemsCommentsMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public CommentLevelCountVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.level);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.level);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.level);
        Integer totalCounts = goodCounts + normalCounts + badCounts;

        // 封装VO,返回前端展示
        CommentLevelCountVO commentLevelCountVO = new CommentLevelCountVO();
        commentLevelCountVO.setGoodCounts(goodCounts);
        commentLevelCountVO.setNormalCounts(normalCounts);
        commentLevelCountVO.setBadCounts(badCounts);
        commentLevelCountVO.setTotalCounts(totalCounts);
        return commentLevelCountVO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult queryPagedComments(
                                        String itemId, Integer level,
                                        Integer page,Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("itemId",itemId);
        map.put("level",level);
        
        // mybatis-pagehelper 分页助手
        // 在查询之前使用分页插件，原理 - 统一拦截sql，为其提供分页功能
        PageHelper.startPage(page,pageSize);
        List<ItemCommentVO> list = itemsCommentsMapper.queryItemComments(map);

        // 脱敏处理
        for (ItemCommentVO vo : list) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }
        return setterPageGrid(list,page);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult searchItems(
            String keywords,String sort, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("sort",sort);

        // mybatis-pagehelper 分页助手
        // 在查询之前使用分页插件，原理 - 统一拦截sql，为其提供分页功能
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> list = itemsMapper.searchItems(map);

        return setterPageGrid(list,page);
    }

    private PagedGridResult setterPageGrid(List<?> list,Integer page){
        // 分页处理
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult gridResult = new PagedGridResult();
        // 当前第几页
        gridResult.setPage(page);
        // list 分页后的数据
        gridResult.setRows(list);
        // total 总页数
        gridResult.setTotal(pageList.getPages());
        // records 总记录数
        gridResult.setRecords(pageList.getTotal());
        return gridResult;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId, Integer level){
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (level != null){
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }
}
