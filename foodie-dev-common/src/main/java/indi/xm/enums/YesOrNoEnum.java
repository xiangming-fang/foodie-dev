package indi.xm.enums;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.enums
 * @ClassName: YesOrNo
 * @Author: albert.fang
 * @Description: 是否 枚举
 * @Date: 2021/10/14 10:21
 */
public enum YesOrNoEnum {

    NO(0,"否"),
    YES(1,"是");

    public Integer type;

    public String des;

    YesOrNoEnum(Integer type, String des){
        this.type = type;
        this.des = des;
    }
}
