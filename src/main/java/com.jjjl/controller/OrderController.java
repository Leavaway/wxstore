package com.jjjl.controller;


import com.jjjl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class OrderController {

    @Autowired
    OrderService orderService;


}

