package com.ahxinin.pay.converter;

import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.gatewayimpl.database.dataobject.PayOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 支付订单转换器
 * @date : 2024-03-21
 */
@Mapper
public interface PayOrderConvert {

    PayOrderConvert INSTANCE = Mappers.getMapper(PayOrderConvert.class);

    PayOrderDO toDataObject(PayOrder payOrder);

    PayOrder toDomainObject(PayOrderDO payOrderDO);
}
