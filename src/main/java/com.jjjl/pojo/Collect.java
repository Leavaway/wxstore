package com.jjjl.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect {

    @MongoId
    ObjectId _id;

    String phone;
    List<CollectGoods> collectGoodsList;

}
