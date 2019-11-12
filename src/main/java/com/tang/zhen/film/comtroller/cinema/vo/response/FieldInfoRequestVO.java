package com.tang.zhen.film.comtroller.cinema.vo.response;

import com.tang.zhen.film.comtroller.common.BaseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import lombok.Data;

import java.io.Serializable;

@Data
public class FieldInfoRequestVO extends BaseVO implements Serializable {
    private  String cinemaId;
    private  String fieldId;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
