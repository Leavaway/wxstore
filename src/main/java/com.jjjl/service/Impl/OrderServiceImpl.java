package com.jjjl.service.Impl;


import com.alibaba.fastjson.JSON;
import com.jjjl.dao.Impl.OrderDaoImpl;
import com.jjjl.dao.OrderDao;
import com.jjjl.pojo.Order;
import com.jjjl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public String getAllOrders(String phone) {
        return JSON.toJSONString(orderDao.getALlOrders(phone));
    }

    @Override
    public String getOrdersByStatus(String phone, int orderStatus) {
        Double orderSta = (double) orderStatus;
        return JSON.toJSONString(orderDao.getOrdersByStatus(phone,orderSta));
    }

}
