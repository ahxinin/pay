package com.ahxinin.pay.configuration;

import com.sankuai.inf.leaf.IDGen;
import com.sankuai.inf.leaf.snowflake.SnowflakeIDGenImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 雪花算法
 * @date : 2024-03-14
 */
@Configuration
public class SnowflakeConfiguration {

    @Bean
    public IDGen initSnowflake(){
        long workId = 1L;
        return new SnowflakeIDGenImpl(workId);
    }
}
