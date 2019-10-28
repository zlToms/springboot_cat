package com.tang.zhen.film.service.common;

import lombok.Data;

@Data
public class CommonServiceException extends Exception {
    private  Integer code;
    private String errMsg;

    public CommonServiceException(int code, String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
