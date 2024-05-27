package com.ahxinin.pay.gatewayimpl.http;

import com.ahxinin.pay.config.AlipayConfig;
import com.ahxinin.pay.constant.PayConstant;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.clientobject.AlipayWapNotifyCO;
import com.ahxinin.pay.gatewayimpl.database.PayNotifyMapper;
import com.ahxinin.pay.gatewayimpl.database.dataobject.PayNotifyDO;
import com.ahxinin.pay.gatewayimpl.http.dataobject.AlipayWapDO;
import com.alibaba.cola.exception.BizException;
import com.alibaba.fastjson2.JSONObject;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.kernel.util.Signer;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description: 支付宝支付能力
 * @date : 2024-03-04
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "pay.enable", havingValue = "true")
public class AlipayMapper {

    @Resource
    private AlipayConfig alipayConfig;
    @Autowired
    private PayNotifyMapper payNotifyMapper;

    /**
     * 支付宝手机网站支付
     * @param alipayWapDO 支付宝手机网站支付DO
     * @return 发起支付凭证
     */
    public String wapPay(AlipayWapDO alipayWapDO) {
        try {
            AlipayTradeWapPayResponse response = Payment.Wap()
                    .optional(PayConstant.ALIPAY_OPTION_TIME_EXPIRE, alipayWapDO.getTimeExpire())
                    .asyncNotify(alipayConfig.getNotifyUrl())
                    .pay(alipayWapDO.getSubject(), alipayWapDO.getOutTradeNo(), alipayWapDO.getTotalAmount(), null, null);
            String payResult = response.getBody();

            log.info("alipay sendRequest, alipayWapDO:{}, response:{}", JSONObject.toJSONString(alipayWapDO), payResult);
            if (!ResponseChecker.success(response)){
                throw new BizException(response.getBody());
            }
            return payResult;
        }catch (Exception e){
            log.error("alipay pay error, alipayWapDO:{}", JSONObject.toJSONString(alipayWapDO), e);
            throw new BizException(e.getMessage());
        }
    }

    /**
     * 支付宝手机网站支付通知
     */
    public PayOrderResult wapNotify(AlipayWapNotifyCO notifyCO){
        //签名校验
        Map<String, String> notifyParams = notifyCO.getNotifyParams();
        boolean verifyResult = Signer.verifyParams(notifyParams, alipayConfig.getAlipayPublicKey());
        if (!verifyResult){
            log.error("alipay notify verify fail, notifyCO:{}", JSONObject.toJSONString(notifyCO));
            throw new BizException("alipay notify verify fail");
        }

        //保存支付通知
        PayNotifyDO payNotifyDO = PayNotifyDO.fromAlipay(notifyCO);
        payNotifyMapper.insert(payNotifyDO);

        //返回交易状态
        return PayOrderResult.alipayState(notifyCO);
    }

    /**
     * 支付宝订单查询
     * @param outTradeNo 商户订单号
     * @return 支付订单
     */
    public PayOrderResult query(String outTradeNo){
        try {
            AlipayTradeQueryResponse response = Payment.Common().query(outTradeNo);
            log.info("alipay query, outTradeNo:{}, response:{}", outTradeNo, JSONObject.toJSONString(response));

            String subCode = response.getSubCode();
            if (StringUtils.endsWithIgnoreCase("ACQ.TRADE_NOT_EXIST", subCode)){
                return PayOrderResult.unknowState(Long.valueOf(outTradeNo));
            }

            if (!ResponseChecker.success(response)){
                throw new BizException(response.getMsg());
            }

            AlipayWapNotifyCO notifyCO = new AlipayWapNotifyCO();
            notifyCO.setTrade_no(response.getTradeNo());
            notifyCO.setOut_trade_no(response.getOutTradeNo());
            notifyCO.setTrade_status(response.getTradeStatus());
            //返回交易状态
            return PayOrderResult.alipayState(notifyCO);
        } catch (Exception e) {
            log.error("alipay query error, outTradeNo:{}", outTradeNo, e);
            throw new BizException(e.getMessage());
        }
    }

}
