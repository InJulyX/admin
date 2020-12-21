package com.admin.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class StrHash {
    public static String getMd5Hash() {
        return "";
    }

    public static String getHmacSHA256Hash(String str, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(str.getBytes(StandardCharsets.UTF_8));
        String sign = new String(Base64.encodeBase64(signData));
        return sign;
    }

    public static void main(String[] args) throws Exception {
        Long timestamp = 1577262236757L;
        String appSecret = "996";
//        String stringToSign = timestamp + "\n" + appSecret;
        String stringToSign = "123456";
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(appSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = new String(Base64.encodeBase64(signData));
        System.out.println(sign);
        System.out.println(getHmacSHA256Hash("123456", "996"));
    }
}
