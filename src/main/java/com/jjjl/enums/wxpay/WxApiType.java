package com.jjjl.enums.wxpay;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WxApiType {
    //下单
    NATIVE_PAY("/v3/pay/transactions/jsapi"),
    //查询订单
    ORDER_QUERY_BY_NO("/v3/pay/transactions/out-trade-no/{out_trade_no}"),
    //关闭订单
    CLOSE_ORDER_BY_NO("/v3/pay/transactions/out-trade-no/{out_trade_no}/close"),
    //申请退款
    DOMESTIC_REFUND("/v3/refund/domestic/refunds"),
    //查询单笔退款
    DOMESTIC_REFUND_QUERY("/v3/refund/domestic/refunds/{out_refund_no}"),
    //申请交易账单
    TRADE_BILL("/v3/bill/tradebill");

    private final String type;

}
