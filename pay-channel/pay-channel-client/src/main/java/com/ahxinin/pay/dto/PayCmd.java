package com.ahxinin.pay.dto;

import lombok.Data;

/**
 * 付款请求
 */
@Data
public class PayCmd {

    //支付金额
    private Long amount;
}
