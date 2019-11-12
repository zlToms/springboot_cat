package com.tang.zhen.film.comtroller.cinema.auth.controller.vo;

import com.tang.zhen.film.comtroller.common.BaseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseVO extends BaseVO {

    private String randomKey;
    private String token;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
