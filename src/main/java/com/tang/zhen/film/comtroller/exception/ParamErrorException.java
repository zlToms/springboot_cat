package com.tang.zhen.film.comtroller.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * VO 参数校验异常
 */
@Data
public class ParamErrorException extends Exception {
    private  Integer code;
    private String errMsg;

    public ParamErrorException(int code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
