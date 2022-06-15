package com.sungp.business.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author sungp
 * @description: 业务错误信息
 * @date 2022年03月11日 17:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceError {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    /**
     * 错误异常信息
     */
    private Object errorMessage;

    public ServiceError(Object errorMsg) {
        this.errorMessage = errorMsg;
        this.timestamp = LocalDateTime.now();
    }
}
