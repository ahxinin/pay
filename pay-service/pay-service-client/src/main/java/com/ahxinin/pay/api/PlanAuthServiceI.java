package com.ahxinin.pay.api;

import com.ahxinin.pay.dto.AuthPlanCmd;
import com.ahxinin.pay.dto.clientobject.PlanAuthCO;
import com.alibaba.cola.dto.SingleResponse;

/**
 * @description: 套餐授权服务
 * @date : 2024-05-11
 */
public interface PlanAuthServiceI {

    /**
     * 根据id获取套餐授权信息
     */
    SingleResponse<PlanAuthCO> getById(Long id);

    /**
     * 授权套餐(用户购买付费)
     */
    SingleResponse<PlanAuthCO> authPlan(AuthPlanCmd authPlanCmd);
}
