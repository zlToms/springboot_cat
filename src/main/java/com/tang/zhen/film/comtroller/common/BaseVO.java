package com.tang.zhen.film.comtroller.common;

import com.tang.zhen.film.comtroller.exception.ParamErrorException;

public abstract class BaseVO {

    public abstract void checkParam() throws ParamErrorException;

}
