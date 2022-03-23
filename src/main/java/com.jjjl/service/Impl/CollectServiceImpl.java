package com.jjjl.service.Impl;


import com.alibaba.fastjson.JSON;
import com.jjjl.dao.CollectDao;
import com.jjjl.pojo.Collect;
import com.jjjl.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectDao collectDao;

    @Override
    public String getAllCollect(String phone) {
        return JSON.toJSONString(collectDao.getAllCollect(phone));
    }

    @Override
    public void setCollect(Collect collect) {
        collectDao.setCollect(collect);
    }

}
