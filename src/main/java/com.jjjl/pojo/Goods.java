package com.jjjl.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    @MongoId
    ObjectId _id;

    double gid;
    String title;
    double price;
    String category;
    double inventory;
    String detail;
    String photo;
}
