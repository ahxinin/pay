package com.ahxinin.pay.gatewayimpl.rpc;

import com.ahxinin.pay.api.PlanAuthServiceI;
import com.ahxinin.pay.convert.AuthPlanConvert;
import com.ahxinin.pay.domain.PlanAuth;
import com.ahxinin.pay.dto.AuthPlanCmd;
import com.ahxinin.pay.dto.UserAuthPlanCmd;
import com.ahxinin.pay.dto.clientobject.PlanAuthCO;
import com.ahxinin.pay.dto.clientobject.UsePlanCO;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @description: 套餐
 * @date : 2024-04-17
 */
@Slf4j
@Component
public class PlanMapper {

    @DubboReference
    private PlanAuthServiceI planAuthService;

    /**
     * 根据id获取套餐授权信息
     */
    public PlanAuth getById(Long id){
        SingleResponse<PlanAuthCO> response = planAuthService.getById(id);
        if (!response.isSuccess()){
            throw new BizException(response.getErrMessage());
        }
        PlanAuthCO planAuthCO = response.getData();
        return AuthPlanConvert.INSTANCE.toPlanAuth(planAuthCO);
    }

    /**
     * 授权套餐
     */
    public UsePlanCO authPlan(Long userId, UserAuthPlanCmd userAuthPlanCmd) {
        AuthPlanCmd authPlanCmd = AuthPlanConvert.INSTANCE.toAuthPlanCmd(userAuthPlanCmd);
        authPlanCmd.setUserId(userId);
        SingleResponse<PlanAuthCO> response = planAuthService.authPlan(authPlanCmd);

        if (!response.isSuccess()) {
            throw new BizException(response.getErrMessage());
        }

        PlanAuthCO planAuthCO = response.getData();
        return AuthPlanConvert.INSTANCE.toUsePlanCO(planAuthCO);
    }
}
