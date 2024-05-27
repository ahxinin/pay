package com.ahxinin.pay.clientobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付订单
 * @date : 2024-03-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderDataCO {

    /**
     * 主键
     */
    private String id;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易标识符
     */
    private String tradeId;

    /**
     * 交易金额
     */
    private Long amount;

    /**
     * 交易名称
     */
    private String name;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 微信公众号openId
     */
    private String weixinMpOpenId;

    /**
     * 支付订单状态
     */
    private String state;
}
