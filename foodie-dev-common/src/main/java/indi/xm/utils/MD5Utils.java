package indi.xm.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.utils
 * @ClassName: MD5Utils
 * @Author: albert.fang
 * @Description: md5加密工具类
 * @Date: 2021/10/11 14:19
 */
public class MD5Utils {

    /**
     * 对字符串进行md5加密
     * @param strValue
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newStr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newStr;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getMD5Str("imooc"));
    }
}
