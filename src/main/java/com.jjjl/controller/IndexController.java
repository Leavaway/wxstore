package com.jjjl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjjl.util.HttpRequest;
import com.jjjl.util.WxPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("phone")
public class IndexController {

    @Autowired
    WxPayConfig wxPayConfig;

    @PostMapping("getPhone")
    public String getPhone(@RequestBody String code) throws IOException {
        String token = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxe8c4d62827bb0bd1&secret=ef7ecbd22075b4a26c92f1e9fdeba051");
        JSONObject accessToken = JSONObject.parseObject(token);
        String tok = accessToken.getString("access_token");
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+tok;
        String phone = HttpRequest.sendPost(url,code);
        return phone;
    }

    @GetMapping("test")
    public String test() throws FileNotFoundException {
        String s = wxPayConfig.getPrivateKeyPath();
        System.out.println(wxPayConfig.getPrivateKey(s));
        return s;
    }



}
