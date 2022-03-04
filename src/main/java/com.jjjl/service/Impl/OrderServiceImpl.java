package com.jjjl.service.Impl;


import com.alibaba.fastjson.JSON;
import com.jjjl.dao.Impl.OrderDaoImpl;
import com.jjjl.pojo.Order;
import com.jjjl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDaoImpl orderDaoImpl;

    @Override
    public String getAllOrders(String phone) {
        String allItems = JSON.toJSONString(orderDaoImpl.getALlOrders(phone));
        return allItems;
    }
}
