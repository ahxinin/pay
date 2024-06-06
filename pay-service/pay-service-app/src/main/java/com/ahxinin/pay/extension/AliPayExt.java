package com.ahxinin.pay.extension;

import com.ahxinin.pay.contants.PlanConstant;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.GetPayResultCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.gatewayimpl.http.AlipayMapper;
import com.ahxinin.pay.gatewayimpl.http.dataobject.AlipayWapDO;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.Extension;
import java.io.UnsupportedEncodingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @description: 支付宝支付
 * @date : 2024-03-14
 */
@Slf4j
@Extension(bizId = PlanConstant.BIZ_ID, useCase = PlanConstant.PAY_CASE_ALI)
public class AliPayExt implements PayExtPt{

    @Resource
    private AlipayMapper alipayMapper;

    @Override
    public PayCO pay(PayOrder payOrder) {
        AlipayWapDO alipayWapDO = AlipayWapDO.fromPrepayOrder(payOrder);
        String result = alipayMapper.wapPay(alipayWapDO);
        try {
            return PayCO.buildAlipayParam(result);
        } catch (UnsupportedEncodingException e) {
            log.error("支付宝支付参数构建失败", e);
            throw new BizException(e.getMessage());
        }
    }

    @Override
    public PayOrderResult queryPayResult(GetPayResultCmd getPayResultCmd) {
        return alipayMapper.query(String.valueOf(getPayResultCmd.getPayOrderId()));
    }
}
