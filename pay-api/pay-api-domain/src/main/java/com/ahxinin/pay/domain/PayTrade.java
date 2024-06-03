package com.ahxinin.pay.domain;

import com.ahxinin.pay.dto.PreTradeCmd;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付交易
 * @date : 2024-03-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayTrade implements Serializable {

    /**
     * 交易名称
     */
    private String name;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 交易金额
     */
    private Long amount;

    /**
     * 交易类型
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

    /**
     * 初始化
     */
    public static PayTrade of(PreTradeCmd preTradeCmd){
        return PayTrade.builder()
                .payType(preTradeCmd.getPayType())
                .tradeType(preTradeCmd.getTradeType())
                .tradeId(preTradeCmd.getTradeId())
                .weixinMpOpenId(preTradeCmd.getWeixinMpOpenId())
                .build();
    }

    /**
     * 更新套餐授权
     */
    public void updatePlanAuth(PlanAuth planAuth){
        this.name = planAuth.getAuthName();
        this.amount = planAuth.getPayPrice();
        this.userId = planAuth.getUserId();
    }
}
