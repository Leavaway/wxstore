package com.jjjl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jjjl.pojo.OrderInfo;
import com.jjjl.util.CreateSignature;
import com.jjjl.util.HttpRequest;
import com.jjjl.util.OrderNoUtils;
import com.jjjl.util.WxPayConfig;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    WxPayConfig wxPayConfig;

    @PostMapping("getPhone")
    public String getPhone(@RequestBody String code) throws IOException {
        String token = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxe8c4d62827bb0bd1&secret=ef7ecbd22075b4a26c92f1e9fdeba051");
        JSONObject accessToken = JSONObject.parseObject(token);
        String tok = accessToken.getString("access_token");
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+tok;
        return HttpRequest.sendPost(url,code);
    }

    @GetMapping("test")
    public String test() throws FileNotFoundException {
        String s = wxPayConfig.getPrivateKeyPath();
        return s;
    }

    @GetMapping("openid")
    public String getOpenId(@RequestParam("code") String code) throws IOException {
        String openid = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session?appid=wxe8c4d62827bb0bd1&secret=ef7ecbd22075b4a26c92f1e9fdeba051&js_code="+code);
        JSONObject jsonObject = JSON.parseObject(openid);
        return jsonObject.getString("openid");
    }

    @PostMapping("notify")
    public void getNotify(HttpServletRequest httpServletRequest){
        System.out.println("---");
        System.out.println(httpServletRequest);
    }


    @PostMapping("prepay")
    public String prepay(@RequestBody OrderInfo orderInfo) throws IOException, NotFoundException, GeneralSecurityException, HttpCodeException {
        CloseableHttpClient httpClient = wxPayConfig.getWxPayClient();

        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type","application/json; charset=utf-8");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();

        String OrderNo = OrderNoUtils.getNo();
        System.out.println("OrderNo: "+OrderNo);

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("mchid",wxPayConfig.getMchId())
                .put("appid", wxPayConfig.getAppid())
                .put("description", orderInfo.getDes())
                .put("notify_url", "https://www.jingjiangjinglian.cn/pay/notify")
                .put("out_trade_no", OrderNo);
        rootNode.putObject("amount")
                .put("total", orderInfo.getAmount());
        rootNode.putObject("payer")
                .put("openid", orderInfo.getOpenid());

        objectMapper.writeValue(bos, rootNode);

        httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
            } else if (statusCode == 204) {
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode+ ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } finally {
            response.close();
        }
        String prepayId = EntityUtils.toString(response.getEntity());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(wxPayConfig.getAppid());
        stringBuilder.append("\n");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime()/1000);
        System.out.println("---");
        stringBuilder.append(timestamp);
        stringBuilder.append("\n");
        String nonceStr = OrderNoUtils.getRandomString(32);
        stringBuilder.append(nonceStr);
        stringBuilder.append("\n");
        stringBuilder.append("prepay_id=");
        JSONObject jsonObject1 = JSONObject.parseObject(bodyAsString);
        String prepay_id = jsonObject1.getString("prepay_id");
        stringBuilder.append(prepay_id);
        stringBuilder.append("\n");
        System.out.println(stringBuilder.toString());
        byte[] bytes = CreateSignature.sign(wxPayConfig.getPrivateKey(wxPayConfig.getPrivateKeyPath()),stringBuilder.toString());
        String paySign = CreateSignature.bytesToHexString(bytes);

        System.out.println("paySign:");
        System.out.println(paySign);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timeStamp",timestamp);
        jsonObject.put("nonceStr",nonceStr);
        jsonObject.put("package",prepay_id);
        jsonObject.put("signType","RSA");
        jsonObject.put("paySign",paySign);
        return JSONObject.toJSONString(jsonObject);


    }

    @GetMapping("find")
    public String findOrder(@RequestParam("orderNo") String orderNo) throws IOException {
        StringBuilder createSign = new StringBuilder();
        createSign.append("GET");
        createSign.append("\n");
        String getUrl = "/v3/pay/transactions/out-trade-no/"+orderNo+"??mchid="+wxPayConfig.getMchId();
        createSign.append(getUrl);
        createSign.append("\n");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime()/1000);
        createSign.append(timestamp);
        createSign.append("\n");
        String nonceStr = OrderNoUtils.getRandomString(32);
        createSign.append(nonceStr);
        createSign.append("\n");
        createSign.append("\n");
        byte[] bytes = CreateSignature.sign(wxPayConfig.getPrivateKey(wxPayConfig.getPrivateKeyPath()),createSign.toString());
        String findSign = CreateSignature.bytesToHexString(bytes);
        StringBuilder header = new StringBuilder();
        header.append("WECHATPAY2-SHA256-RSA2048 mchid=\"");
        header.append(wxPayConfig.getMchId());
        header.append("\",nonce_str=\"");
        header.append(nonceStr);
        header.append("\",signature=\"");
        header.append(findSign);
        header.append("\",timestamp=\"");
        header.append(timestamp);
        header.append("\",serial_no=\"");
        header.append(wxPayConfig.getMchSerialNo());
        header.append("\"");
        System.out.println("header:");
        System.out.println(header.toString());

        //create http request
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        String url = "https://api.mch.weixin.qq.com"+getUrl;
        GetMethod getMethod = new GetMethod(url);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Authorization", header.toString());
        getMethod.addRequestHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
        getMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586");
        getMethod.addRequestHeader("Content-Type", "application/json");

        httpClient.executeMethod(getMethod);

        String result = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return result;
    }



}
