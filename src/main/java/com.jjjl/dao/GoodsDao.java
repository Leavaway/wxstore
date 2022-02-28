package com.jjjl.dao;

import com.jjjl.pojo.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GoodsDao {
    public Goods getGoodsDetailById(double gid);

    public List<Goods> getGoodsByCategory(String category);

    public List<Goods> getAllItems();
}
