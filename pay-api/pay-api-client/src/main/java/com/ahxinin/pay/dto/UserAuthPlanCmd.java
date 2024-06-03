package com.ahxinin.pay.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description: 用户授权套餐
 * @date : 2024-04-17
 */
@Data
public class UserAuthPlanCmd implements Serializable {

    /**
     * 套餐Id
     */
    @NotNull(message = "套餐id不能为空")
    private Long planId;

    /**
     * 套餐金额
     */
    @NotNull(message = "套餐金额不能为空")
    private Long planPrice;
}
