package com.jjjl.util;


import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

@Configuration
@PropertySource("classpath:wxpay.properties")
@ConfigurationProperties(prefix = "wxpay")
@Data
public class WxPayConfig {

    private String mchId;
    private String mchSerialNo;
    private String privateKeyPath;
    private String apiV3Key;
    private String appid;
    private String domain;

    /**
     * 获取商户私钥文件
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public PrivateKey getPrivateKey(String filename) throws FileNotFoundException {
        return PemUtil.loadPrivateKey(
                new FileInputStream(filename));
    }

    public CloseableHttpClient getWxPayClient() throws IOException, HttpCodeException, GeneralSecurityException, NotFoundException {
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
// 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(mchId, new WechatPay2Credentials(mchId,
                new PrivateKeySigner(mchSerialNo, getPrivateKey(privateKeyPath))), apiV3Key.getBytes(StandardCharsets.UTF_8));
// ... 若有多个商户号，可继续调用putMerchant添加商户信息

// 从证书管理器中获取verifier
        Verifier verifier = certificatesManager.getVerifier(mchId);
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, getPrivateKey(privateKeyPath))
                .withValidator(new WechatPay2Validator(verifier));
// ... 接下来，你仍然可以通过builder设置各种参数，来配置你的HttpClient

// 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签，并进行证书自动更新
        CloseableHttpClient httpClient = builder.build();

// 后面跟使用Apache HttpClient一样
//        CloseableHttpResponse response = httpClient.execute(...);
        return httpClient;
    }




}
