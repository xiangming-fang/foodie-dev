package indi.xm.enums;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.enums
 * @ClassName: AddressEnum
 * @Author: albert.fang
 * @Description: 地址
 * @Date: 2021/10/15 14:45
 */
public enum AddressEnum {

    YES(1,"默认地址"),
    NO(0,"非默认地址");

    public Integer type;
    public String des;

    AddressEnum(Integer type,String des){
        this.type = type;
        this.des = des;
    }
}
