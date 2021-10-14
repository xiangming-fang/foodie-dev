package indi.xm.vo;

import indi.xm.pojo.Items;
import indi.xm.pojo.ItemsImg;
import indi.xm.pojo.ItemsParam;
import indi.xm.pojo.ItemsSpec;

import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.vo
 * @ClassName: InfoVO
 * @Author: albert.fang
 * @Description: 商品详情VO
 * @Date: 2021/10/14 14:54
 */
public class InfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }
}
