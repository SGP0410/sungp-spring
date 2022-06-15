package com.sungp.business.result;

/**
 * @author sungp
 * @description: 响应码枚举类
 * @date 2022年03月05日 20:54
 */
public enum HttpCodes {

    SUCCESS("200" , "success"),
    RESOURCES_NOT_FOUNT("404" , "资源未找到"),
    PARAMETER_ERROR("400", "参数校验未通过"),
    SERVER_EXCEPTION("500" , "服务器遇到错误，无法完成请求");

    private final String code;
    private final String msg;

    HttpCodes(String code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
