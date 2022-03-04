package com.jjjl.dao;

import com.jjjl.pojo.Order;

import java.util.List;

public interface OrderDao {

    public List<Order> getALlOrders(String phone);
}
