package com.jjjl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    NOTPAY("未支付"),
    SUCCESS("支付成功"),
    CLOSED("超时已关闭"),
    CANCEL("用户已取消"),
    REFUND_PROCESSING("退款中"),
    REFUND_SUCCESS("已退款"),
    REFUND_ABNORMAL("退款异常");

    private final String type;


}
