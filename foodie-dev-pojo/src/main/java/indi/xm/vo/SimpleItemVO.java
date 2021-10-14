package indi.xm.vo;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.vo
 * @ClassName: SimpleItemVO
 * @Author: albert.fang
 * @Description: 简单商品类型
 * @Date: 2021/10/14 13:46
 */
public class SimpleItemVO {

    private String itemId;
    private String itemName;
    private String itemUrl;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
