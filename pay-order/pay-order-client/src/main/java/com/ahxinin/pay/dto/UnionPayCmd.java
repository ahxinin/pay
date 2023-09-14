package com.ahxinin.pay.dto;

import lombok.Data;

/**
 * @description: 统一支付参数
 * @date : 2023-09-14
 */
@Data
public class UnionPayCmd {

    /**
     * 支付金额
     */
    private Long amount;

    /**
     * 交易物品
     */
    private String subject;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 场景
     */
    private String scene;
}
