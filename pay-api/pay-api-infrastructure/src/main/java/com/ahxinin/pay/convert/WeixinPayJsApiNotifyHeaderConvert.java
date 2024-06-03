package com.ahxinin.pay.convert;

import com.ahxinin.pay.dto.clientobject.WeixinPayNotifyHeaderCO;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyHeaderCmd;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 微信支付通知头部数据
 * @date : 2024-04-12
 */
@Mapper
public interface WeixinPayJsApiNotifyHeaderConvert {

    WeixinPayJsApiNotifyHeaderConvert INSTANCE = Mappers.getMapper(WeixinPayJsApiNotifyHeaderConvert.class);

    /**
     * 微信支付通知头部数据
     */
    WeixinPayJsApiNotifyHeaderCmd convert(WeixinPayNotifyHeaderCO weixinPayNotifyHeaderCO);
}
