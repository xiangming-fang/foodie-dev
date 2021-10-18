package indi.xm.enums;

import java.io.File;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.enums
 * @ClassName: ConstantEnum
 * @Author: albert.fang
 * @Description: 常量
 * @Date: 2021/10/18 13:56
 */
public enum ConstantEnum {

    UPLOAD_USER_FACE_LOCATION(File.separator + "faces"),

    PREFIX_FACE("face-");

    public String value;

    ConstantEnum(String value){
        this.value = value;
    }
}
