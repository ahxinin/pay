package com.ahxinin.pay.service;

import com.ahxinin.pay.api.PayServiceI;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.WeixinMpAuthCmd;
import com.ahxinin.pay.dto.WeixinMpInitCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpAuthCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpInitCO;
import com.ahxinin.pay.enums.PayTypeEnum;
import com.ahxinin.pay.enums.TradeTypeEnum;
import com.alibaba.cola.dto.SingleResponse;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 支付服务测试类
 * @date : 2024-05-30
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PayServiceTest {

    @Resource
    private PayServiceI payService;

    @Test
    public void weixinMpInitTest(){
        WeixinMpInitCmd weixinMpInitCmd = new WeixinMpInitCmd();
        weixinMpInitCmd.setTradeType(TradeTypeEnum.USER_PLAN.getCode());
        weixinMpInitCmd.setTradeId(123456L);
        SingleResponse<WeixinMpInitCO> response = payService.weixinMpInit(weixinMpInitCmd);
        log.info("weixinMpUrl:{}", response.getData());
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void weixinMpAuthTest(){
        WeixinMpAuthCmd weixinMpAuthCmd = new WeixinMpAuthCmd();
        weixinMpAuthCmd.setCode("081jo410070sbS1Dnm0006fKG11jo41o");
        SingleResponse<WeixinMpAuthCO> response = payService.weixinMpAuth(weixinMpAuthCmd);
        log.info("weixin openid:{}", response.getData());
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void pay(){
        PayCmd payCmd = new PayCmd();
        payCmd.setName("test");
        payCmd.setPayType(PayTypeEnum.ALIPAY.getCode());
        payCmd.setAmount(1L);
        payCmd.setTradeType(TradeTypeEnum.USER_PLAN.getCode());
        payCmd.setTradeId(System.currentTimeMillis());
        payCmd.setUserId(1L);
        SingleResponse<PayCO> response = payService.pay(payCmd);
        log.info("payCO:{}", response.getData());
        Assert.assertTrue(response.isSuccess());
    }
}
