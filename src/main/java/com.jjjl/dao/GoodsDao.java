package com.jjjl.dao;

import com.jjjl.pojo.Goods;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao {
    public Goods getGoodsDetailById(double gid);
}
