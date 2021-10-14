package indi.xm.enums;

import io.swagger.models.auth.In;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.enums
 * @ClassName: CommentLevel
 * @Author: albert.fang
 * @Description: 商品评价等级
 * @Date: 2021/10/14 15:20
 */
public enum CommentLevel {

    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"坏评");

    public Integer level;
    public String des;

    CommentLevel(Integer level,String des){
        this.level = level;
        this.des = des;
    }
}
