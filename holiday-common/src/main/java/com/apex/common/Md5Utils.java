package com.apex.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


/**  
 * @desc: holiday-common  
 * @author: yangcheng  
 * @createTime: 2018年4月18日 下午5:04:09    
 * @version: v1.0    
 */    
public final class Md5Utils {

    /**  
     * constructor of Md5Utils.java
     */   
    private Md5Utils() {

    }

    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月18日 下午5:04:18    
     * @param str String
     * @return String  
     */  
    @SuppressWarnings("restriction")
    public static String encoderByMd5(String str) {
        // 确定计算方法
        MessageDigest md5;
        String newstr = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
            return newstr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月18日 下午5:04:43    
     * @param text String
     * @return String  
     */  
    public static String encoderByMd5Ext(String text) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月18日 下午5:05:48    
     * @param text String
     * @param md5Str String
     * @return boolean  
     */  
    public static boolean verify(String text, String md5Str) {
        String md5Text = encoderByMd5(text);
        if(md5Text.equalsIgnoreCase(md5Str))
        {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }
    
    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月18日 下午5:06:40    
     * @param args String[]  
     */  
    public static void main(String[] args) {
        System.out.println(encoderByMd5("01234"));
        System.out.println(encoderByMd5Ext("01234"));
    }

}
