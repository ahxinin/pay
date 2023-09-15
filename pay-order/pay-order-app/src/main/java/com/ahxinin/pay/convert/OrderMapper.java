package com.ahxinin.pay.convert;

import com.ahxinin.pay.domain.order.Order;
import com.ahxinin.pay.dto.UnionPayCmd;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 订单转换
 * @date : 2023-09-15
 */
@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(UnionPayCmd unionPayCmd);
}
