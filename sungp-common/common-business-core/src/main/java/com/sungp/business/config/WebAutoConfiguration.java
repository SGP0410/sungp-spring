package com.sungp.business.config;

import com.sungp.business.exception.GlobalException;
import com.sungp.business.response.GlobalResponseHandler;
import org.springframework.context.annotation.Bean;

/**
 * 配置类
 * @author sungp
 * @description: 配置类
 * @date 2022年03月06日 11:04
 */
public class WebAutoConfiguration {

    /**
     * @description: 全局异常处理
     * @author sungp
     * @date 2022年03月07日 11:38
     * @return GlobalException
     */
    @Bean
    public GlobalException getGlobalException(){
        return new GlobalException();
    }

    /**
     * @description: 全局分装返回结果
     * @author sungp
     * @date 2022年03月13日 0:14
     * @return GlobalResponseHandler
     */
    @Bean
    public GlobalResponseHandler getGlobalResponseHandler(){
        return new GlobalResponseHandler();
    }


}
