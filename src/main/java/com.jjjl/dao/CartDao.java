package com.jjjl.dao;


import com.jjjl.pojo.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao {
    public Cart getCart(String phone);
    public void setCart(Cart cart);
}
