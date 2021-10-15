package indi.xm.enums;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.enums
 * @ClassName: Category
 * @Author: albert.fang
 * @Description: 首页类别
 * @Date: 2021/10/14 10:47
 */
public enum CategoryLevelEnum {

    FIRST(1,"一级分类"),
    SECOND(2,"二级分类"),
    THIRD(3,"三级分类");

    public Integer level;
    public String des;

    CategoryLevelEnum(Integer level, String des){
        this.level = level;
        this.des = des;
    }
}
