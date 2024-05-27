package com.ahxinin.pay.gatewayimpl.database;

import com.ahxinin.pay.dto.ListPayOrderQuery;
import com.ahxinin.pay.gatewayimpl.database.dataobject.PayOrderDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 支付订单
 * @date : 2024-03-20
 */
@Mapper
public interface PayOrderMapper {

    /**
     * 插入支付订单
     */
    void insert(PayOrderDO payOrderDO);

    /**
     * 更新支付订单状态
     */
    void updateState(PayOrderDO payOrderDO);

    /**
     * 更新支付订单状态为已支付
     */
    int updateStatePaidFromPending(PayOrderDO payOrderDO);

    /**
     * 更新支付订单状态为已取消
     */
    int updateStateCancelFromPending(PayOrderDO payOrderDO);

    /**
     * 构建业务编号查询支付订单
     */
    PayOrderDO getByTradeId(Long tradeId);

    /**
     * 根据ID查询支付订单
     */
    PayOrderDO getById(Long id);

    /**
     * 查询支付订单
     */
    List<PayOrderDO> listPayOrder(ListPayOrderQuery listPayOrderQuery);

    /**
     * 查询支付订单数量
     */
    Long countPayOrder(ListPayOrderQuery listPayOrderQuery);
}
