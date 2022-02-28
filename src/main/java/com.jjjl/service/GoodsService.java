package com.jjjl.service;

import com.jjjl.pojo.Goods;

import java.util.ArrayList;

public interface GoodsService {

    public String getGoodsDetailById(int gid);

    public String getGoodsByCategory(String category);

    public String getAllItems();

}
