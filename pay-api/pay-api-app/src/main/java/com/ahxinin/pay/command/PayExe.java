package com.ahxinin.pay.command;

import com.ahxinin.pay.domain.PayTrade;
import com.ahxinin.pay.domain.PrePayResult;
import com.ahxinin.pay.dto.PreTradeCmd;
import com.ahxinin.pay.extension.TradeExtPt;
import com.ahxinin.pay.factory.PayGatewayFactory;
import com.ahxinin.pay.gateway.PayGateway;
import com.ahxinin.pay.gatewayimpl.rpc.PayMapper;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.extension.ExtensionExecutor;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 支付
 * @date : 2024-03-19
 */
@Slf4j
@Component
public class PayExe {

    @Resource
    private PayMapper payMapper;
    @Resource
    private PayGatewayFactory payGatewayFactory;
    @Resource
    private ExtensionExecutor extensionExecutor;

    /**
     * 微信公众号授权url
     */
    public SingleResponse<String> getWeixinMpAuthPageUrl(String tradeType, Long tradeId){
        String url = payMapper.getWeixinMpAuthPageUrl(tradeType, tradeId);
        return SingleResponse.of(url);
    }

    /**
     * 微信公众号openId
     */
    public SingleResponse<String> getWeixinMpOpenId(String code) {
        String openId = payMapper.getWeixinMpOpenId(code);
        return SingleResponse.of(openId);
    }

    /**
     * 预支付交易
     */
    public SingleResponse createPreTrade(PreTradeCmd preTradeCmd) {
        PayGateway payGateway = payGatewayFactory.get(preTradeCmd.getPayType());
        if (payGateway == null){
            return SingleResponse.buildFailure("1000","不支持的支付类型");
        }

        PayTrade payTrade = extensionExecutor.execute(TradeExtPt.class, preTradeCmd.getBizScenario(), ex -> ex.createPayTrade(preTradeCmd));
        PrePayResult preTrade = payGateway.prePay(payTrade);
        return SingleResponse.of(preTrade);
    }
}
