package com.jjjl.controller;


import com.jjjl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("detail")
    public String getDetail(@RequestParam("phone") String phone, @RequestParam("orderStatus") int orderStatus){
        if(orderStatus==1){
            return orderService.getAllOrders(phone);
        }else if(orderStatus==2||orderStatus==3){
            return orderService.getOrdersByStatus(phone,orderStatus);
        }
        return "error orderStatus";
    }

}

