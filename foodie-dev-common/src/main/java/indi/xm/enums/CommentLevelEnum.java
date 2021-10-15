package indi.xm.enums;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.enums
 * @ClassName: CommentLevel
 * @Author: albert.fang
 * @Description: 商品评价等级
 * @Date: 2021/10/14 15:20
 */
public enum CommentLevelEnum {

    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"坏评");

    public Integer level;
    public String des;

    CommentLevelEnum(Integer level, String des){
        this.level = level;
        this.des = des;
    }
}
