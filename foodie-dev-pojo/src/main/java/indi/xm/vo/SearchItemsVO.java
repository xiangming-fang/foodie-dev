package indi.xm.vo;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.vo
 * @ClassName: SearchItemsVO
 * @Author: albert.fang
 * @Description: 展示商品搜索的VO
 * @Date: 2021/10/14 19:04
 */
public class SearchItemsVO {

    private String itemId;

    private String itemName;

    private int sellCounts;

    private String imgUrl;

    // 金额都以int保存，以分为单位
    private int price;

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

    public int getSellCounts() {
        return sellCounts;
    }

    public void setSellCounts(int sellCounts) {
        this.sellCounts = sellCounts;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
