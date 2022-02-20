package com.jjjl.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jjjl.dao.GoodsDao;
import com.jjjl.dao.Impl.GoodsDaoImpl;
import com.jjjl.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDaoImpl goodsDaoImpl;

    @Override
    public String getGoodsDetailById(int gid) {
        Double d = (double) gid;
        String goods = JSON.toJSONString(goodsDaoImpl.getGoodsDetailById(d)) ;
        return goods;
    }
}
