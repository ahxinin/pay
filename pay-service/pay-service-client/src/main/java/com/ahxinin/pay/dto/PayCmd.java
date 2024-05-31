package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 购买资源
 * @date : 2024-03-14
 */
@Data
public class PayCmd implements Serializable {

    /**
     * 交易名称
     */
    private String name;

    /**
     * 支付方式
     * @see com.ahxinin.pay.enums.PayTypeEnum
     */
    private String payType;

    /**
     * 交易金额
     */
    private Long amount;

    /**
     * 交易类型
     * @see com.ahxinin.pay.enums.TradeTypeEnum
     */
    private String tradeType;

    /**
     * 交易id
     */
    private Long tradeId;

    /**
     * 微信公众号openId
     */
    private String weixinMpOpenId;

    /**
     * 用户id
     */
    private Long userId;
}
