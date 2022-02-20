package com.jjjl.controller;

import com.alibaba.fastjson.JSON;
import com.jjjl.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("detail")
    public String getDetail(@RequestParam("gid")int gid){
        String test = goodsService.getGoodsDetailById(gid);
        return test;
    }
}
