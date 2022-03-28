package com.jjjl.util;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;



public class WxSign {


    // Authorization: <schema> <token>
// GET - getToken("GET", httpurl, "")
// POST - getToken("POST", httpurl, json)
    static String schema = "WECHATPAY2-SHA256-RSA2048";
//    static HttpUrl httpurl = HttpUrl.parse(url);
    static WxPayConfig wxPayConfig;

    public String getToken(String method, HttpUrl url, String body) throws UnsupportedEncodingException, NoSuchAlgorithmException, FileNotFoundException, SignatureException, InvalidKeyException {
        String nonceStr = OrderNoUtils.getRandomString(32);
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = sign(message.getBytes("utf-8"));

        return "mchid=\"" + "1623473870" + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + "139DA01850AF4599B290A69FB601D597F496AAE4" + "\","
                + "signature=\"" + signature + "\"";
    }

    public String sign(byte[] message) throws SignatureException, FileNotFoundException, NoSuchAlgorithmException, InvalidKeyException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(PemUtil.loadPrivateKey(
                new FileInputStream("apiclient_key.pem")));
        sign.update(message);

        return Base64.getEncoder().encodeToString(sign.sign());
    }

    public String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
        String canonicalUrl = url.encodedPath();
        if (url.encodedQuery() != null) {
            canonicalUrl += "?" + url.encodedQuery();
        }

        return method + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + body + "\n";
    }
}
