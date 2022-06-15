package com.sungp.business.exception;

import com.sungp.business.result.HttpCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sungp
 * @description: 自定义异常
 * @date 2022年03月06日 11:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException{

    private String code;
    private String data;

    /**
     * @description: 业务异常
     * @author sungp
     * @date 2022年06月08日 0:45
     * @return null
     */
    public ServiceException(String data){
        super(HttpCodes.SERVER_EXCEPTION.getMsg());
        this.code = HttpCodes.SERVER_EXCEPTION.getCode();
        this.data = data;
    }

    /**
     * @description: 自定义响应码异常构造
     * @author sungp
     * @date 2022年03月06日 20:49
     * @param codes 响应码
     * @return null
     */
    public ServiceException(HttpCodes codes , String data){
        super(codes.getMsg());
        this.code = codes.getCode();
        this.data = data;
    }

    /**
     * @description: 特殊响应构造
     * @author sungp
     * @date 2022年03月06日 20:51
     * @param code 响应码
     * @param msg 消息
     * @return null
     */
    public ServiceException(String code , String msg , String data){
        super(msg);
        this.code = code;
        this.data = data;
    }

    public ServiceException(){}

}
