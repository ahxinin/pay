package com.ahxinin.pay.faced;

import com.ahxinin.pay.contants.PlanConstant;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.ahxinin.pay.enums.PayOrderStateEnum;
import com.ahxinin.pay.extension.TradeExtPt;
import com.ahxinin.pay.gateway.PayOrderGateway;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 支付封装
 * @date : 2024-03-21
 */
@Slf4j
@Component
public class PayFaced {
    @Resource
    private PayOrderGateway payOrderGateway;
    @Resource
    private ExtensionExecutor extensionExecutor;

    @Transactional(rollbackFor = Exception.class)
    public PayNotifyCO notify(PayOrderResult payOrderResult){
        if (payOrderResult.success()){
            return notifySuccess(payOrderResult.getId());
        }
        if (payOrderResult.cancel()){
            //更新数据
            payOrderGateway.updateCancel(payOrderResult.getId());
            //更新缓存
            payOrderGateway.updateStateCache(payOrderResult.getId(), PayOrderStateEnum.CANCEL.getCode());
            return PayNotifyCO.cancel();
        }
        return PayNotifyCO.failed();
    }

    /**
     * 支付成功通知处理
     */
    private PayNotifyCO notifySuccess(Long id){
        //更新支付订单状态
        Boolean result = payOrderGateway.updatePaid(id);
        if (!result){
            log.error("支付订单状态更新失败：id:{}", id);
            return PayNotifyCO.failed();
        }

        //更新缓存
        payOrderGateway.updateStateCache(id, PayOrderStateEnum.PAID.getCode());
        PayOrder payOrder = payOrderGateway.getById(id);

        //更新业务授权状态
        BizScenario bizScenario = BizScenario.valueOf(PlanConstant.BIZ_ID, payOrder.getTradeType());
        PayNotifyCO payNotifyCO = extensionExecutor.execute(TradeExtPt.class, bizScenario, ex -> ex.notifySuccess(id));
        log.info("支付成功通知处理结果：id:{}, result:{}", id, payNotifyCO);
        return payNotifyCO;
    }
}
