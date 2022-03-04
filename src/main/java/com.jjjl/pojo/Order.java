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
public class Order {
    @MongoId
    ObjectId _id;

    String phone;
    String orderSerialNum;
    List<OrderGoods> orderDetails;
    double orderPrice;

}
