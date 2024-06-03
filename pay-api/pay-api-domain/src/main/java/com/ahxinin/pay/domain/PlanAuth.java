package com.ahxinin.pay.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 套餐授权
 * @date : 2024-04-17
 */
@Data
public class PlanAuth implements Serializable {

    /**
     * 授权id
     */
    private Long authId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 授权名称
     */
    private String authName;

    /**
     * 支付金额
     */
    private Long payPrice;
}
