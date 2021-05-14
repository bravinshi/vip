package com.goldensky.framework.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/13 15:48
 * 包名： com.goldensky.framework.util
 * 类说明：
 */
public class SignUtils {

    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, "MD5");
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, String signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
//            if (k.equals(WXPayConstants.FIELD_SIGN)) {
//                continue;
//            }
            if(data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if ("MD5".equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        }
//        else if (WXPayConstants.SignType.HMACSHA256.equals(signType)) {
//            return HMACSHA256(sb.toString(), key);
//        }
        else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
}
