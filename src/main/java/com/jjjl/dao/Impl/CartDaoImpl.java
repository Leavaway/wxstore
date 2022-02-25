package com.jjjl.dao.Impl;

import com.jjjl.dao.CartDao;
import com.jjjl.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Cart getCart(String phone) {
        Query query=new Query(Criteria.where("phone").is(phone));
        Cart cart = mongoTemplate.findOne(query, Cart.class);
        return cart;
    }

    @Override
    public void setCart(Cart cart) {
        Query query=new Query(Criteria.where("phone").is(cart.getPhone()));
        Update update=new Update();
        update.set("goods",cart.getGoods());
        update.set("phone",cart.getPhone());
        mongoTemplate.updateFirst(query,update,Cart.class);
    }
}
