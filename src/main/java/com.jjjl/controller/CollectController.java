package com.jjjl.controller;



import com.jjjl.pojo.Cart;
import com.jjjl.pojo.Collect;
import com.jjjl.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    @GetMapping("check")
    public String getDetail(@RequestParam("phone") String phone){
        return collectService.getAllCollect(phone);
    }

    @PostMapping("setCheck")
    public void setDetail(@RequestBody Collect collect){
        collectService.setCollect(collect);
    }

}
