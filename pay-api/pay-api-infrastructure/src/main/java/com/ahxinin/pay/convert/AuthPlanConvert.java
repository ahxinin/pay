package com.ahxinin.pay.convert;

import com.ahxinin.pay.domain.PlanAuth;
import com.ahxinin.pay.dto.AuthPlanCmd;
import com.ahxinin.pay.dto.UserAuthPlanCmd;
import com.ahxinin.pay.dto.clientobject.PlanAuthCO;
import com.ahxinin.pay.dto.clientobject.UsePlanCO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 套餐授权
 * @date : 2024-04-17
 */
@Mapper
public interface AuthPlanConvert {

    AuthPlanConvert INSTANCE = Mappers.getMapper(AuthPlanConvert.class);

    AuthPlanCmd toAuthPlanCmd(UserAuthPlanCmd userAuthPlanCmd);

    PlanAuth toPlanAuth(PlanAuthCO planAuthCO);

    UsePlanCO toUsePlanCO(PlanAuthCO planAuthCO);
}
