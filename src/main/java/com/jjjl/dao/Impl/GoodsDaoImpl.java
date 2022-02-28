package com.jjjl.dao.Impl;

import com.jjjl.dao.GoodsDao;
import com.jjjl.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Goods getGoodsDetailById(double gid) {
        Query query=new Query(Criteria.where("gid").is(gid));
        Goods goods = mongoTemplate.findOne(query,Goods.class);
        return goods;
    }

    @Override
    public List<Goods> getGoodsByCategory(String category) {
        Query query=new Query(Criteria.where("category").is(category));
        List<Goods> goodsList= mongoTemplate.find(query,Goods.class);
        return goodsList;
    }

    @Override
    public List<Goods> getAllItems() {
        Query query=new Query(Criteria.where("").is(""));
        List<Goods> goodsList= mongoTemplate.find(query,Goods.class);
        return goodsList;
    }

}
