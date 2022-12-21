package edu.zut.domain;

import lombok.Data;

@Data
public class AliPay {
    //订单号
    private String traceNo;
    //金额
    private String totalAmount;
    private String subject;
//    private String alipayTraceNo;
}

