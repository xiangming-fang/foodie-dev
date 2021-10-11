package indi.xm.enums;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.enums
 * @ClassName: Sex
 * @Author: albert.fang
 * @Description: 性别枚举
 * @Date: 2021/10/11 14:30
 */
public enum Sex {

    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");

    public final Integer type;

    public final String value;

    Sex(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
