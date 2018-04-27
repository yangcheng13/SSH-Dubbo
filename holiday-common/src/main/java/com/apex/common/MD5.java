
package com.apex.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @desc: holiday-common
 * @author: yangcheng
 * @createTime: 2018年4月23日 上午11:39:41
 * @version: v1.0
 */
public class MD5 {

    /**
     * constructor of MD5.java
     */
    public MD5() {}

    /**
     * @author: yangcheng
     * @createTime: 2018年4月23日 上午11:39:51
     * @param b byte[]
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public static String md5(byte b[]) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(b, 0, b.length);
        return byteArrayToHexString(md5.digest());
    }

    /**
     * @author: yangcheng
     * @createTime: 2018年4月23日 上午11:40:35
     * @param data String
     * @return byte[]
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static byte[] md5(String data)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        return md5.digest(data.getBytes("UTF-8"));
    }

    /**
     * @author: yangcheng
     * @createTime: 2018年4月23日 上午11:41:15
     * @param plainText String
     * @return String
     */
    public static String Md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString().substring(8, 24);// 16位的加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            sb.append(byteToHexString(b[i]));

        return sb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

}
