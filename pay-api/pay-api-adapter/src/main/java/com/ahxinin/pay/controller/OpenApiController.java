package com.ahxinin.pay.controller;

import com.ahxinin.pay.clientobject.AlipayNotifyCO;
import com.ahxinin.pay.clientobject.WeixinPayNotifyCO;
import com.ahxinin.pay.clientobject.WeixinPayNotifyHeaderCO;
import com.ahxinin.pay.command.PayNotifyExe;
import com.alibaba.fastjson2.JSONObject;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 对外开放接口
 * @date : 2024-05-24
 */
@Slf4j
@RestController
@RequestMapping("/api/open")
public class OpenApiController {

    @Resource
    private PayNotifyExe payNotifyExe;

    /**
     * 微信公众号服务器验证
     */
    @GetMapping("weixin/validate")
    public ResponseEntity<String> weixinApiValidate(
            @RequestParam(required = false) String signature,
            @RequestParam(required = false) String timestamp,
            @RequestParam(required = false) String nonce,
            @RequestParam(required = false) String echostr){
        log.info("weixinApiValidate signature:{}, timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
        return ResponseEntity.ok(echostr);
    }

    /**
     * 微信公众号支付购买资源回调
     */
    @PostMapping("weixin/notify")
    public ResponseEntity<String> weixinApiNotify(@RequestBody JSONObject params, HttpServletRequest request){
        log.info("weixinApiNotify params:{}", params);
        WeixinPayNotifyCO weixinPayNotifyCO = params.toJavaObject(WeixinPayNotifyCO.class);
        WeixinPayNotifyHeaderCO weixinPayNotifyHeaderCO = init(request);
        weixinPayNotifyCO.setHeader(weixinPayNotifyHeaderCO);

        String result = payNotifyExe.weixinNotify(params, weixinPayNotifyCO);
        return ResponseEntity.ok(result);
    }

    /**
     * 支付宝支付购买资源回调
     */
    @PostMapping(value = "ali/notify", produces = "application/x-www-form-urlencoded")
    public ResponseEntity<String> aliApiNotify(@RequestParam Map<String, String> params){
        log.info("aliApiNotify params:{}", params);
        AlipayNotifyCO alipayNotifyCO = AlipayNotifyCO.build(params);
        String result = payNotifyExe.alipayWapNotify(alipayNotifyCO);
        return ResponseEntity.ok(result);
    }

    public static WeixinPayNotifyHeaderCO init(HttpServletRequest request){
        WeixinPayNotifyHeaderCO weixinPayNotifyHeaderCO = new WeixinPayNotifyHeaderCO();
        weixinPayNotifyHeaderCO.setSignature(request.getHeader("Wechatpay-Signature"));
        weixinPayNotifyHeaderCO.setSerial(request.getHeader("Wechatpay-Serial"));
        weixinPayNotifyHeaderCO.setNonce(request.getHeader("Wechatpay-Nonce"));
        weixinPayNotifyHeaderCO.setTimestamp(request.getHeader("Wechatpay-Timestamp"));
        weixinPayNotifyHeaderCO.setSignatureType(request.getHeader("Wechatpay-Signature-Type"));
        return weixinPayNotifyHeaderCO;
    }
}
