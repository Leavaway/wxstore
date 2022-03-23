package com.jjjl.dao;

import com.jjjl.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    public List<Order> getALlOrders(String phone);

    public List<Order> getOrdersByStatus(String phone,double orderStatus);
}
