package com.example.asus.car.Utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by asus on 2016/8/2.
 */
public class MD5Utils {
    /*
    * MD5加密
    * */
    public static String encode(String password){
        try {
            //MD5都是32位
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for(byte b : digest){
                int i = b & 0xff; //获取字节的低八位有效值
                //将整数转为16进制
                String hexString = Integer.toHexString(i);
                if(hexString.length()<2){
                    hexString = "0"+hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //没有该算法时抛出异常
        }
        return "";
    }
}
