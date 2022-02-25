package com.jjjl.controller;


import com.jjjl.pojo.Cart;
import com.jjjl.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("getcart")
    public String getDetail(@RequestParam("phone")String phone){
        String cart = cartService.getCart(phone);
        return cart;
    }

    @PostMapping("setcart")
    public void setDetail(@RequestBody Cart cart){
        cartService.setCart(cart);
    }
}
