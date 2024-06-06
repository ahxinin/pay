package com.ahxinin.pay.gatewayimpl;

import com.ahxinin.pay.gateway.CommonGateway;
import com.ahxinin.pay.gatewayimpl.inner.SnowFlakeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @description: 通用功能
 * @date : 2024-03-14
 */
@Component
public class CommonGatewayImpl implements CommonGateway {

    @Resource
    private SnowFlakeMapper snowFlakeMapper;

    @Override
    public Long getSnowflakeId() {
        String key = "pay";
        return snowFlakeMapper.getId(key);
    }
}