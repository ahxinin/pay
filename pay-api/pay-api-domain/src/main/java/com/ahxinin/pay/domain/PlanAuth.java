package com.ahxinin.pay.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 套餐授权
 * @date : 2024-04-17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 初始化
     */
    public static PlanAuth of(){
        return PlanAuth.builder()
                .userId(1L)
                .authName("测试")
                .payPrice(1L)
                .build();
    }
}
