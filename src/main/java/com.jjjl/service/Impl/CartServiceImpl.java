package com.jjjl.service.Impl;


import com.alibaba.fastjson.JSON;
import com.jjjl.dao.CartDao;
import com.jjjl.dao.Impl.CartDaoImpl;
import com.jjjl.pojo.Cart;
import com.jjjl.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public String getCart(String photo) {
        return JSON.toJSONString(cartDao.getCart(photo));
    }

    @Override
    public void setCart(Cart cart) {
        cartDao.setCart(cart);
    }
}
