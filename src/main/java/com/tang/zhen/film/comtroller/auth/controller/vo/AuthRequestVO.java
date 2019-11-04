package com.tang.zhen.film.comtroller.auth.controller.vo;

import com.tang.zhen.film.comtroller.common.BaseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
public class AuthRequestVO  extends BaseVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
