package com.jjjl.dao;


import com.jjjl.pojo.Collect;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectDao {

    public Collect getAllCollect(String phone);

    public void setCollect(Collect collect);
}
