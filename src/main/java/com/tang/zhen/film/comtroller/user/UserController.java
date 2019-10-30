package com.tang.zhen.film.comtroller.user;

import com.tang.zhen.film.common.utils.ToolUtils;
import com.tang.zhen.film.comtroller.common.BaseResponseVO;
import com.tang.zhen.film.comtroller.exception.NextFilmException;
import com.tang.zhen.film.service.common.CommonServiceException;
import com.tang.zhen.film.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController  //默认所有的返回都做Responsebody处理
@RequestMapping(value = "/nextfilm/user/")
public class UserController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @RequestMapping(value = "check",method = RequestMethod.POST)
    public BaseResponseVO checkUser(String username) throws CommonServiceException, NextFilmException {

        if(ToolUtils.isEmpty(username)){
            throw new NextFilmException(1,"username不能为空");
        }

        boolean isSuccess =userServiceAPI.checkUserName(username);

       if(isSuccess){
           return BaseResponseVO.serviceFaied("用户名已存在");
       }else{
           return BaseResponseVO.success();
        }
    }
}
