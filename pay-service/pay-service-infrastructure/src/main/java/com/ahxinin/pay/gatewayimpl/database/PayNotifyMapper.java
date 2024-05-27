package com.ahxinin.pay.gatewayimpl.database;

import com.ahxinin.pay.gatewayimpl.database.dataobject.PayNotifyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 支付通知
 * @date : 2024-03-05
 */
@Mapper
public interface PayNotifyMapper {

    /**
     * 插入
     * @param payNotifyDO 支付通知
     */
    void insert(PayNotifyDO payNotifyDO);
}
