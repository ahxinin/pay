package com.ahxinin.pay.convert;

import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.dto.ListPayOrderQuery;
import com.ahxinin.pay.dto.clientobject.PayOrderCO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 支付订单
 * @date : 2024-03-26
 */
@Mapper
public interface PayOrderConvert {

    PayOrderConvert INSTANCE = Mappers.getMapper(PayOrderConvert.class);

    PayOrder toDomainObject(PayOrderCO payOrderCO);

}
