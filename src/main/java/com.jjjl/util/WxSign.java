package com.jjjl.util;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    WxPayConfig wxPayConfig;

    String getToken(String method, HttpUrl url, String body) throws UnsupportedEncodingException, NoSuchAlgorithmException, FileNotFoundException, SignatureException, InvalidKeyException {
        String nonceStr = "your nonce string";
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = sign(message.getBytes("utf-8"));

        return "mchid=\"" + wxPayConfig.getMchId() + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + wxPayConfig.getMchSerialNo() + "\","
                + "signature=\"" + signature + "\"";
    }

    String sign(byte[] message) throws SignatureException, FileNotFoundException, NoSuchAlgorithmException, InvalidKeyException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(wxPayConfig.getPrivateKey(wxPayConfig.getPrivateKeyPath()));
        sign.update(message);

        return Base64.getEncoder().encodeToString(sign.sign());
    }

    String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
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
