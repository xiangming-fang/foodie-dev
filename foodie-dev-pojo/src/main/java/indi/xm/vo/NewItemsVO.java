package indi.xm.vo;

import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.vo
 * @ClassName: NewItemsVO
 * @Author: albert.fang
 * @Description: 各个分类下推荐的6个最新商品
 * @Date: 2021/10/14 13:43
 */
public class NewItemsVO {

    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemVOS;

    public Integer getRootCatId() {
        return rootCatId;
    }

    public void setRootCatId(Integer rootCatId) {
        this.rootCatId = rootCatId;
    }

    public String getRootCatName() {
        return rootCatName;
    }

    public void setRootCatName(String rootCatName) {
        this.rootCatName = rootCatName;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<SimpleItemVO> getSimpleItemVOS() {
        return simpleItemVOS;
    }

    public void setSimpleItemVOS(List<SimpleItemVO> simpleItemVOS) {
        this.simpleItemVOS = simpleItemVOS;
    }
}
