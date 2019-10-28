package com.tang.zhen.film.service.common;

import lombok.Data;

@Data
public class DataTransactionException extends Exception {
    private  Integer code;
    private String errMsg;

    public DataTransactionException(int code,String errMsg){
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
