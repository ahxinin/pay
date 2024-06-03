package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 套餐授权
 * @date : 2024-04-17
 */
@Data
public class AuthPlanCmd implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 套餐id
     */
    private Long planId;

    /**
     * 套餐价格
     */
    private Long planPrice;

    /**
     * 创建授权名称
     */
    public String createAuthName(){
        return "彗光翻译平台订阅会员服务";
    }
}
