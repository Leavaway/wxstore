package com.jjjl.service;

import com.jjjl.pojo.Order;

import java.util.List;

public interface OrderService {

    public String getAllOrders(String phone);

    public String getOrdersByStatus(String phone, int orderStatus);
}
