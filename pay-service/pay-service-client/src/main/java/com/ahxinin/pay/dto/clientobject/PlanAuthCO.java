package com.ahxinin.pay.dto.clientobject;

import com.ahxinin.pay.dto.AuthPlanCmd;
import java.io.Serializable;
import lombok.Data;

/**
 * @description: 套餐授权
 * @date : 2024-04-17
 */
@Data
public class PlanAuthCO implements Serializable {

    /**
     * 授权id
     */
    private Long authId;

    /**
     * 授权名称
     */
    private String authName;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 支付金额
     */
    private Long payPrice;

    public static PlanAuthCO of(AuthPlanCmd authPlanCmd) {
        PlanAuthCO planAuthCO = new PlanAuthCO();
        planAuthCO.setAuthName(authPlanCmd.createAuthName());
        planAuthCO.setUserId(authPlanCmd.getUserId());
        return planAuthCO;
    }

    public void updateAuthId(Long authId) {
        this.authId = authId;
    }

    public void updatePayPrice(Long payPrice) {
        this.payPrice = payPrice;
    }
}
