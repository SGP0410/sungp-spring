package com.sungp.business.response;

import com.alibaba.fastjson2.JSON;
import com.sungp.business.result.ResultMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author sungp
 * @description: 返回结果统一分装
 * @date 2022年03月11日 10:50
 */
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    /**
     * 这个组件是否支持给定的控制器方法返回类型和选择的{@code HttpMessageConverter}类型。
     * 这里整合swagger出现了问题，swagger相关的不拦截
     *
     * @param returnType    返回类型
     * @param converterType 所选变频器类型
     * @return 如果 {@code true} 则 beforeBodyWrite() 应该被调用;
     * {@code false} 否则
     */
    @Override

    public boolean supports(MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        //对所有类型的结果都支持,和swagger相关的不拦截
        return !returnType.getDeclaringClass().getName().contains("springfox");
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body                  要写的正文
     * @param returnType            控制器方法的返回类型
     * @param selectedContentType   通过内容协商选择的内容类型
     * @param selectedConverterType 选择要写入响应的转换器类型
     * @param request               当前请求
     * @param response              当前的反应
     * @return 传入的主体或修改过的(可能是新的)实例
     */
    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        //如果body是，统一异常处理后的ResultMessage，无需处理直接返回
        if (body instanceof ResultMessage) {
            return body;
        }
        ResultMessage<Object> resultMessage = ResultMessage.ok(body);
        if (body instanceof String) {
            return JSON.toJSONString(resultMessage);
        }
        return ResultMessage.ok(body);
    }
}
