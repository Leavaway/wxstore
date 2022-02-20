package com.jjjl.dao.Impl;

import com.jjjl.dao.GoodsDao;
import com.jjjl.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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

}
