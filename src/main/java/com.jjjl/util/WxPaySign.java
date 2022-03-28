package com.jjjl.util;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import okhttp3.HttpUrl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class WxPaySign {

    // Authorization: <schema> <token>
// GET - getToken("GET", httpurl, "")
// POST - getToken("POST", httpurl, json)
    static String schema = "WECHATPAY2-SHA256-RSA2048";
    //    static HttpUrl httpurl = HttpUrl.parse(url);

    public String getToken(String appid,String nonceStr,String timestamp,String prepay_id) throws UnsupportedEncodingException, NoSuchAlgorithmException, FileNotFoundException, SignatureException, InvalidKeyException {
        String message = buildMessage(appid,nonceStr, timestamp, prepay_id);
        String signature = sign(message.getBytes("utf-8"));

        return signature;
    }

    public String sign(byte[] message) throws SignatureException, FileNotFoundException, NoSuchAlgorithmException, InvalidKeyException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(PemUtil.loadPrivateKey(
                new FileInputStream("apiclient_key.pem")));
        sign.update(message);

        return Base64.getEncoder().encodeToString(sign.sign());
    }

    public String buildMessage(String appid, String nonceStr,String timestamp, String prepay_id) {

        return  appid + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + prepay_id + "\n";
    }
}
