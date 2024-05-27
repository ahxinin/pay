package com.ahxinin.pay.assembler;

import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.clientobject.PayOrderCO;
import com.ahxinin.pay.gatewayimpl.database.dataobject.PayOrderDO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 支付订单
 * @date : 2024-03-26
 */
@Mapper
public interface PayOrderAssembler {

    PayOrderAssembler INSTANCE = Mappers.getMapper(PayOrderAssembler.class);

    PayOrderCO toClientObject(PayOrderDO orderDO);

    PayOrderCO toClientObject(PayOrder payOrder);

    List<PayOrderCO> toClientObjectList(List<PayOrderDO> orderDOList);

    PayOrder toPayOrder(PayCmd payCmd);

    PayOrderResult toPayOrderResult(PayOrder payOrder);
}
