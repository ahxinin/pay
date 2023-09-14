package com.ahxinin.pay.order;

import com.ahxinin.pay.domain.order.OrderState;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @description: 订单数据表
 * @date : 2023-09-14
 */
@Data
public class OrderDO {

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
    private Integer state;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
