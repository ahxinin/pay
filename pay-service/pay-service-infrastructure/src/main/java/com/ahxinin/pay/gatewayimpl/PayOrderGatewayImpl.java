package com.ahxinin.pay.gatewayimpl;

import com.ahxinin.pay.constant.PayConstant;
import com.ahxinin.pay.converter.PayOrderConvert;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.gateway.CommonGateway;
import com.ahxinin.pay.gateway.PayOrderGateway;
import com.ahxinin.pay.gatewayimpl.database.PayOrderMapper;
import com.ahxinin.pay.gatewayimpl.database.dataobject.PayOrderDO;
import com.alibaba.fastjson2.JSONObject;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description: 支付订单网关
 * @date : 2024-03-21
 */
@Slf4j
@Component
public class PayOrderGatewayImpl implements PayOrderGateway {

    @Resource
    private PayOrderMapper payOrderMapper;
    @Resource
    private CommonGateway commonGateway;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void createPayOrder(PayOrder payOrder) {
        //更新实际支付金额
        payOrder.updatePayAmount();

        PayOrderDO payOrderDO = PayOrderConvert.INSTANCE.toDataObject(payOrder);
        payOrderDO.setId(commonGateway.getSnowflakeId());
        payOrderDO.setDeleted(false);
        payOrderDO.setCreatedAt(System.currentTimeMillis());
        payOrderMapper.insert(payOrderDO);
    }

    @Override
    public void createPaidOrder(PayOrder payOrder) {
        payOrder.paid();
        createPayOrder(payOrder);
    }

    @Override
    public Boolean updatePaid(Long id) {
        PayOrderDO payOrderDO = new PayOrderDO();
        payOrderDO.setId(id);
        payOrderDO.setUpdatedAt(System.currentTimeMillis());
        int result = payOrderMapper.updateStatePaidFromPending(payOrderDO);
        log.info("updatePaid id:{} result:{}", id, result);
        return result > 0;
    }

    @Override
    public Boolean updateCancel(Long id) {
        PayOrderDO payOrderDO = new PayOrderDO();
        payOrderDO.setId(id);
        payOrderDO.setUpdatedAt(System.currentTimeMillis());
        int result = payOrderMapper.updateStateCancelFromPending(payOrderDO);
        log.info("updateCancel id:{} result:{}", id, result);
        return result > 0;
    }

    @Override
    public PayOrder getById(Long id) {
        PayOrderDO payOrderDO = payOrderMapper.getById(id);
        return PayOrderConvert.INSTANCE.toDomainObject(payOrderDO);
    }

    @Override
    public void updateStateCache(Long id, String state) {
        String cacheKey = getCacheKey(id);
        redisTemplate.opsForHash().put(cacheKey, PayConstant.CACHE_KEY_STATE, state);
        redisTemplate.expire(cacheKey, PayConstant.EXPIRE_MINUTES, TimeUnit.MINUTES);
    }

    @Override
    public String getStateCache(Long id) {
        String cacheKey = getCacheKey(id);
        Object object = redisTemplate.opsForHash().get(cacheKey, PayConstant.CACHE_KEY_STATE);
        if (object == null){
            return null;
        }
        return object.toString();
    }

    @Override
    public void addPayCache(PayCmd payCmd, PayOrder payOrder, PayCO payCO) {
        String cacheKey = getCacheKey(payCmd.getTradeId());
        String paramCacheKey = getParamCacheKey(payCmd.getTradeId(), payCmd.getPayType());

        redisTemplate.opsForHash().put(paramCacheKey, PayConstant.CACHE_KEY_PARAM, payCO);
        redisTemplate.opsForHash().put(cacheKey, PayConstant.CACHE_KEY_PAYORDER, JSONObject.toJSONString(payOrder));

        redisTemplate.expire(paramCacheKey, PayConstant.EXPIRE_MINUTES, TimeUnit.MINUTES);
        redisTemplate.expire(cacheKey, PayConstant.EXPIRE_MINUTES, TimeUnit.MINUTES);
    }

    @Override
    public PayCO getPayParamCache(PayCmd payCmd) {
        String cacheKey =getParamCacheKey(payCmd.getTradeId(), payCmd.getPayType());
        Object object = redisTemplate.opsForHash().get(cacheKey, PayConstant.CACHE_KEY_PARAM);
        if (object==null){
            return null;
        }
        return (PayCO) object;
    }

    @Override
    public PayOrder getPayOrderCache(Long tradeId) {
        String cacheKey = getCacheKey(tradeId);
        Object object = redisTemplate.opsForHash().get(cacheKey, PayConstant.CACHE_KEY_PAYORDER);
        if (object != null){
            return JSONObject.parseObject(object.toString(), PayOrder.class);
        }
        PayOrderDO payOrderDO = payOrderMapper.getByTradeId(tradeId);
        return PayOrderConvert.INSTANCE.toDomainObject(payOrderDO);
    }

    /**
     * 支付缓存key
     */
    private String getCacheKey(Long id){
        return "pay:"+id;
    }

    /**
     * 支付参数缓存key
     */
    private String getParamCacheKey(Long id, String payType){
        return "pay:"+id+":"+payType;
    }
}
