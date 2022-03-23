package com.jjjl.dao.Impl;


import com.jjjl.dao.CollectDao;
import com.jjjl.pojo.Cart;
import com.jjjl.pojo.Collect;
import com.jjjl.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CollectDaoImpl implements CollectDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Collect getAllCollect(String phone) {
        Query query=new Query(Criteria.where("phone").is(phone));
        return mongoTemplate.findOne(query, Collect.class);
    }

    @Override
    public void setCollect(Collect collect) {
        Query query=new Query(Criteria.where("phone").is(collect.getPhone()));
        Update update=new Update();
        update.set("phone",collect.getPhone());
        update.set("collectGoodsList",collect.getCollectGoodsList());
        mongoTemplate.updateFirst(query,update, Collect.class);
    }
}
