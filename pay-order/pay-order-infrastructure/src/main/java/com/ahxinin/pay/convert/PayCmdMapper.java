package com.ahxinin.pay.convert;

import com.ahxinin.pay.domain.order.Order;
import com.ahxinin.pay.dto.PayCmd;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @date : 2023-09-15
 */
@Mapper
public interface PayCmdMapper {

    PayCmdMapper INSTANCE = Mappers.getMapper(PayCmdMapper.class);

    PayCmd toPayCmd(Order order);
}
