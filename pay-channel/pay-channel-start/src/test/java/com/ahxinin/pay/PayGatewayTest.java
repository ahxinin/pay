package com.ahxinin.pay;

import com.ahxinin.pay.domain.customer.Pay;
import com.ahxinin.pay.domain.customer.gateway.PayGateway;
import com.ahxinin.pay.dto.PayCmd;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PayGatewayTest {

    @Autowired
    private PayGateway payGateway;

    @Test
    public void createTrade(){
        PayCmd payCmd = PayCmd.builder()
                .subject("test")
                .amount(1000L)
                .tradeNo("123")
                .channel("alipay")
                .scene("web")
                .build();
        Pay pay = payGateway.createTrade(payCmd);
        log.info("pay:{}", JSONObject.toJSONString(pay));
    }
}
