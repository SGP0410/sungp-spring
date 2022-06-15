package com.sungp.business.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sungp
 * @description: 返回结果
 * @date 2022年03月05日 20:42
 */
@Data
public class ResultMessage<T> implements Serializable {
    /**
     * 响应码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public ResultMessage(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultMessage() {
    }

    /**
     * @description: 成功的返回结果
     * @author sungp
     * @date 2022年03月05日 21:10
     * @param data 返回数据
     * @return ResultMessage<T>
     */
    public static <T> ResultMessage<T> ok(T data){
        return new ResultMessage<T>(
                HttpCodes.SUCCESS.getCode() ,
                HttpCodes.SUCCESS.getMsg() ,
                data
        );
    }

    /**
     * @description: 创建返回结果
     * @author sungp
     * @date 2022年03月05日 21:13
     * @param code 响应码
     * @param msg 响应消息
     * @param data 返回数据
     * @return ResultMessage<T>
     */
    public static <T> ResultMessage<T> create(String code , String msg , T data){
        return new ResultMessage<T>(
                code , msg , data
        );
    }

    /**
     * @description: 创建资源未找到的返回结果
     * @author sungp
     * @date 2022年03月05日 21:19
     * @param data 资源未找到的提示数据
     * @return ResultMessage<T>
     */
    public static <T> ResultMessage<T> createResourcesNotFount(T data){
        return new ResultMessage<T>(
                HttpCodes.RESOURCES_NOT_FOUNT.getCode(),
                HttpCodes.RESOURCES_NOT_FOUNT.getMsg(),
                data
        );
    }

    /**
     * @description: 创建参数异常返回结果
     * @author sungp
     * @date 2022年03月06日 10:53
     * @param data 参数异常提示信息
     * @return ResultMessage<T>
     */
    public static <T> ResultMessage<T> createParameterError(T data){
        return new ResultMessage<T>(
                HttpCodes.PARAMETER_ERROR.getCode(),
                HttpCodes.PARAMETER_ERROR.getMsg(),
                data
        );
    }

    /**
     * @description: 创建服务器异常返回结果
     * @author sungp
     * @date 2022年03月06日 10:57
     * @param data 服务器异常信息
     * @return ResultMessage<T>
     */
    public static <T> ResultMessage<T> createServerException(T data){
        return new ResultMessage<T>(
                HttpCodes.SERVER_EXCEPTION.getCode(),
                HttpCodes.SERVER_EXCEPTION.getMsg(),
                data
        );
    }
}
