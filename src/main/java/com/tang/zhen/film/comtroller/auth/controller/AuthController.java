package com.tang.zhen.film.comtroller.auth.controller;

import com.tang.zhen.film.comtroller.auth.controller.vo.AuthRequestVO;
import com.tang.zhen.film.comtroller.auth.controller.vo.AuthResponseVO;
import com.tang.zhen.film.comtroller.auth.util.JwtTokenUtil;
import com.tang.zhen.film.comtroller.common.BaseResponseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import com.tang.zhen.film.service.common.CommonServiceException;
import com.tang.zhen.film.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {

    @Autowired
    private  JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceAPI userServiceAPI;

    @RequestMapping(value = "auth",method = RequestMethod.POST)
    public BaseResponseVO auth(@RequestBody AuthRequestVO authRequestVO) throws ParamErrorException, CommonServiceException {

        authRequestVO.checkParam();

        boolean isValid = userServiceAPI.userAuth(authRequestVO.getUsername()
                , authRequestVO.getPassword());

        if(isValid){
            String randomKey = jwtTokenUtil.getRandomKey();
            String token =jwtTokenUtil.generateToken(authRequestVO.getUsername(),randomKey);
            AuthResponseVO authResponseVO = AuthResponseVO.builder()
                                            .randomKey(randomKey)
                                            .token(token).build();
            return BaseResponseVO.success(authResponseVO);
        }else{
            return BaseResponseVO.serviceFaied("1","用户名或密码不正确");
        }
    }
}
