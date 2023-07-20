package com.ahxinin.pay.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 付款请求
 */
@Data
@Builder
public class PayCmd {

    //交易编号
    private String tradeNo;

    //支付金额
    private Long amount;

    //交易物品
    private String subject;

    public double convertRealAmount(){
       return new BigDecimal(amount).divide(new BigDecimal(100),2, RoundingMode.HALF_DOWN).doubleValue();
    }
}
