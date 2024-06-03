package com.ahxinin.pay.factory;

import com.ahxinin.pay.gateway.PayGateway;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date : 2024-04-18
 */
@Component
public class PayGatewayFactory implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Map<String, PayGateway> PAY_MAP;

    public PayGateway get(String payType) {
        return PAY_MAP.get(payType);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, PayGateway> beanOfType = applicationContext.getBeansOfType(PayGateway.class);
        PAY_MAP = Optional.of(beanOfType)
                .map(beanOfTypeMap -> beanOfTypeMap.values().stream()
                        .collect(Collectors.toMap(PayGateway::getType, Function.identity())))
                .orElse(ImmutableMap.of());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
