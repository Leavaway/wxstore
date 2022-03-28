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
    String address;
    String expressNumber;
    List<OrderGoods> orderDetails;
    double orderTotalPrice;
    double orderTotalNum;
    String orderPhoto;
    /* 设置订单状态码
    *  1.0 代表所有订单
    *  2.0 代表订单正在处理
    *  3.0 代表订单存在纠纷
    * */
    double orderStatus;

}
