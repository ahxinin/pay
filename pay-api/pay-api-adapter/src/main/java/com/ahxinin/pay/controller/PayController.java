package com.ahxinin.pay.controller;

import com.ahxinin.pay.command.PayExe;
import com.ahxinin.pay.command.PayQueryCmdExe;
import com.ahxinin.pay.domain.PrePayResult;
import com.ahxinin.pay.dto.PreTradeCmd;
import com.ahxinin.pay.dto.clientobject.PayEventCO;
import com.ahxinin.pay.dto.clientobject.PayOrderDataCO;
import com.alibaba.cola.dto.SingleResponse;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 支付
 * @date : 2024-03-18
 */
@Slf4j
@RestController
@RequestMapping("/api/pay")
public class PayController {

    @Resource
    private PayExe payExe;
    @Resource
    private PayQueryCmdExe payQueryCmd;

    /**
     * 获取微信公众号授权地址
     */
    @GetMapping("weixin/getAuthUrl")
    @ResponseBody
    public SingleResponse<String> weixinMPInit(@RequestParam String tradeType, @RequestParam Long tradeId){
        if (tradeId == null){
            return SingleResponse.buildFailure("1000","tradeId不能为空");
        }
        return payExe.getWeixinMpAuthPageUrl(tradeType, tradeId);
    }


    /**
     * 获取微信公众号openId
     */
    @GetMapping("weixin/getOpenId")
    @ResponseBody
    public SingleResponse<String> weixinMPAuth(@RequestParam String code){
        if (StringUtils.isEmpty(code)){
            return SingleResponse.buildFailure("1000","code不能为空");
        }
        return payExe.getWeixinMpOpenId(code);
    }

    /**
     * 预支付交易
     */
    @PostMapping("preTrade")
    @ResponseBody
    public SingleResponse<PrePayResult> createPreTrade(@RequestBody @Validated PreTradeCmd preTradeCmd){
        if ("weixin".equals(preTradeCmd.getPayType())){
            preTradeCmd.checkWeixinPayParam();
        }
        return payExe.createPreTrade(preTradeCmd);
    }

    /**
     * 获取支付订单
     * @param tradeId 交易id
     */
    @GetMapping("getPayOrder")
    @ResponseBody
    public SingleResponse<PayOrderDataCO> getPayOrder(@RequestParam Long tradeId){
        return payQueryCmd.getOrderByTradeId(tradeId);
    }

    /**
     * 获取支付结果
     * @param payOrderId 支付订单id
     */
    @GetMapping("getPayResult")
    @ResponseBody
    public SingleResponse<PayEventCO> getPayResult(@RequestParam Long payOrderId){
        return payQueryCmd.getPayResult(payOrderId);
    }
}
