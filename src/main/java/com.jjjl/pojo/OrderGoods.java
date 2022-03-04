package com.jjjl.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoods {

    double gid;
    String title;
    double price;
    double orderGoodNum;

}
