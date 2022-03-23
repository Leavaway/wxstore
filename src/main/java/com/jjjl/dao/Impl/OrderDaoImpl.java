package com.jjjl.dao.Impl;

import com.jjjl.dao.OrderDao;
import com.jjjl.pojo.Goods;
import com.jjjl.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Order> getALlOrders(String phone) {
        Query query=new Query(Criteria.where("phone").is(phone));
        return mongoTemplate.find(query,Order.class);
    }

    @Override
    public List<Order> getOrdersByStatus(String phone, double orderStatus) {
        Query query=new Query(Criteria.where("phone").is(phone).and("orderStatus").is(orderStatus));
        return mongoTemplate.find(query,Order.class);
    }
}
