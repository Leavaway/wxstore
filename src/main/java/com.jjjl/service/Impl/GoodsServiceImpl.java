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
    GoodsDao goodsDao;

    @Override
    public String getGoodsDetailById(int gid) {
        Double d = (double) gid;
        return JSON.toJSONString(goodsDao.getGoodsDetailById(d));
    }

    @Override
    public String getGoodsByCategory(String category) {
        return JSON.toJSONString(goodsDao.getGoodsByCategory(category));
    }

    @Override
    public String getAllItems() {
        return JSON.toJSONString(goodsDao.getAllItems());
    }
}
