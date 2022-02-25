package com.jjjl.service.Impl;


import com.alibaba.fastjson.JSON;
import com.jjjl.dao.Impl.CartDaoImpl;
import com.jjjl.pojo.Cart;
import com.jjjl.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDaoImpl cartDaoImpl;

    @Override
    public String getCart(String photo) {
        String cart = JSON.toJSONString(cartDaoImpl.getCart(photo)) ;
        return cart;
    }

    @Override
    public void setCart(Cart cart) {
        cartDaoImpl.setCart(cart);
    }
}
