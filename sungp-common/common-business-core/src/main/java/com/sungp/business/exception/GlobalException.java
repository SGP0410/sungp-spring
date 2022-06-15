package com.sungp.business.exception;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.sungp.business.result.ResultMessage;
import com.sungp.business.utils.HttpUtils;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * @author sungp
 * @description: 全局异常处理
 * @date 2022年03月06日 11:05
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * @param exception 异常
     * @return ResultMessage
     * @description: Feign异常处理
     * @author sungp
     * @date 2022年03月11日 15:30
     */
    @ExceptionHandler(FeignException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage<ServiceError> feignException(FeignException exception) {
        log.error("[Feign - Exception] - 捕获到Feign异常!", exception);
        /*
         * 由于上游服务也同样使用了统一数据处理，不管正常还是异常，响应
         * 的都是ResultMessage 。所以这里的responseBody()其实就是
         * ResultMessage 的json 字符串 。
         */
        Optional<ByteBuffer> byteBuffer = exception.responseBody();
        //判断是否有值
        if (byteBuffer.isPresent()) {
            String json = StandardCharsets.UTF_8.decode(byteBuffer.get()).toString();
            //json转实体类
            return JSON.parseObject(json, new TypeReference<ResultMessage<ServiceError>>() {}.getType());
        }
        return ResultMessage.createServerException(new ServiceError("调用下游服务器异常"));
    }

    /**
     * @param exception 异常
     * @return ResultMessage<Set < String>>
     * @description: 参数校验统一异常处理
     * @author sungp
     * @date 2022年03月06日 21:22
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage<ServiceError> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        //获得绑定的返回对象
        BindingResult result = exception.getBindingResult();

        Set<String> set = result.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        log.error("[MethodArgumentNotValid - Exception] - 捕获到参数校验异常！", exception);
        return ResultMessage.createParameterError(new ServiceError(set));
    }

    /**
     * @param exception 异常
     * @return ResultMessage<String>
     * @description: 自定义业务异常处理
     * @author sungp
     * @date 2022年03月06日 21:18
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage<ServiceError> serviceExceptionHandler(ServiceException exception) {
        log.error("[Service - Exception] - 捕获到业务异常信息！", exception);
        return ResultMessage.create(exception.getCode(), exception.getMessage(), new ServiceError(exception.getData()));
    }

    /**
     * @param throwable 异常
     * @return ResultMessage<String>
     * @description: 全局异常处理
     * @author sungp
     * @date 2022年03月07日 14:09
     */
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultMessage<ServiceError> globalExceptionHandler(Throwable throwable) {
        log.error("[Global - Exception] - 捕获到全局异常！");

        //获取异常请求的信息
        HttpServletRequest request = HttpUtils.getRequest();

        String url = request.getRequestURL().toString();
        log.error("[Global - Exception] - 请求的url - {}", url);

        log.error("[Global - Exception] - 异常信息 - ", throwable);

        return ResultMessage.createServerException(new ServiceError(throwable.getMessage()));
    }

}
