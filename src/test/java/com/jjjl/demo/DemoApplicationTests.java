package com.jjjl.demo;

import com.jjjl.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @MongoId
    String id;

    @Test
    void contextLoads() {
        double d = 100.0;
        Query query=new Query(Criteria.where("gid").is(1.0));
        System.out.println("---");
        System.out.println(query);
        System.out.println("---");
        Goods goods = mongoTemplate.findOne(query,Goods.class);
        System.out.println(goods.get_id());
        System.out.println(goods.getPhoto());
    }

    @Test
    void testDouble(){
        double d1 = 1.0;
        double d2 = 1.0;
        System.out.println(d1);
        System.out.println(1.0==1.0);
    }

}
