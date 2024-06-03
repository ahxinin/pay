package com.ahxinin.pay.assembler;

import com.ahxinin.pay.dto.clientobject.PayOrderDataCO;
import com.ahxinin.pay.domain.PayOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 支付订单
 * @date : 2024-03-26
 */
@Mapper
public interface PayOrderAssembler {

    PayOrderAssembler INSTANCE = Mappers.getMapper(PayOrderAssembler.class);

    PayOrderDataCO toClientObject(PayOrder payOrder);
}
