package com.ahxinin.pay.domain.order;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * 支付订单
 */
@Data
@Builder
public class Order{

    /**
     * id
     */
    private Long id;

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

    /**
     * 订单状态
     */
    private OrderState state;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建订单
     */
    public Order create(Long amount, String subject, String channel, String scene){
        return Order.builder()
                .amount(amount)
                .subject(subject)
                .channel(channel)
                .scene(scene)
                .state(OrderState.CREATED)
                .build();
    }
}
