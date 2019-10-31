package com.tang.zhen.film.comtroller.common.handler;

import com.tang.zhen.film.comtroller.common.BaseResponseVO;
import com.tang.zhen.film.comtroller.exception.NextFilmException;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import com.tang.zhen.film.service.common.CommonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice  //spring4开始提供的 植入的过程 但凡引入，所有的controller都要经过一遍
public class GlobalExceptionHandler {

    @ExceptionHandler(NextFilmException.class) //拦截什么样的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody  //返回一个json
    public BaseResponseVO nextFilmException(NextFilmException e){
        return BaseResponseVO.serviceFaied(e.getErrMsg());
    }

    @ExceptionHandler(CommonServiceException.class) //拦截什么样的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody  //返回一个json
    public BaseResponseVO commonServiceException(CommonServiceException e){
        return BaseResponseVO.serviceFaied(e.getCode(),e.getErrMsg());
    }

    @ExceptionHandler(ParamErrorException.class) //拦截什么样的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody  //返回一个json
    public BaseResponseVO paramErrorException(ParamErrorException e){
        return BaseResponseVO.serviceFaied(e.getCode(),e.getErrMsg());
    }


    @ExceptionHandler(Exception.class) //拦截什么样的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody  //返回一个json
    public BaseResponseVO commonServiceException(Exception e){
        return BaseResponseVO.systemError();
    }
}
