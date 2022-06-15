package com.sungp.tool.config;

import com.sungp.tool.aop.ExecutionTimeAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sungp
 * @description: TODO
 * @date 2022年06月15日 22:53
 */
@Configuration
public class BeanConfig {

    @Bean
    public ExecutionTimeAop getExecutionTimeAop(){
        return new ExecutionTimeAop();
    }

}
