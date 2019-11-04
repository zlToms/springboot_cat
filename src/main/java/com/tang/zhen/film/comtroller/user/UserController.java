package com.tang.zhen.film.comtroller.user;

import com.tang.zhen.film.common.utils.ToolUtils;
import com.tang.zhen.film.comtroller.common.BaseResponseVO;
import com.tang.zhen.film.comtroller.common.TraceUtil;
import com.tang.zhen.film.comtroller.exception.NextFilmException;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import com.tang.zhen.film.comtroller.user.vo.EnrollUserVO;
import com.tang.zhen.film.comtroller.user.vo.UserInfoVO;
import com.tang.zhen.film.service.common.CommonServiceException;
import com.tang.zhen.film.service.user.UserServiceAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController  //默认所有的返回都做Responsebody处理
@RequestMapping(value = "/user/")
@Api("用户相关的API")
public class UserController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @ApiOperation(value = "用户名重复验证",notes = "用户名重复验证")
    @ApiImplicitParam(name = "username"
            ,value="待验证的用户名称",paramType = "query",required = true,dataType = "string")
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

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public BaseResponseVO register(@RequestBody EnrollUserVO enrollUserVO) throws CommonServiceException, NextFilmException, ParamErrorException {
        //贫血模型 ： 一个类里面只有get set方法是很浪费的
        //领域模型  贫血模型  充血模型
        //有点：Controller类里面不用加验证  更加简洁
        enrollUserVO.checkParam();

        userServiceAPI.userEnroll(enrollUserVO);

        return BaseResponseVO.success();
    }

    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public BaseResponseVO describeUserInfo() throws CommonServiceException, ParamErrorException {

        String userId = TraceUtil.getUserId();
        UserInfoVO userInfoVO = userServiceAPI.describeUserInfo(userId);

        userInfoVO.checkParam();


        return BaseResponseVO.success(userInfoVO);
    }

    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public BaseResponseVO updateUserInfo(@RequestBody UserInfoVO userInfoVO) throws CommonServiceException, ParamErrorException {

        userInfoVO.checkParam();
        UserInfoVO result = userServiceAPI.updateUserInfo(userInfoVO);
        userInfoVO.checkParam();
        return BaseResponseVO.success(result);
    }

    @RequestMapping(value = "lougout",method = RequestMethod.POST)
    public BaseResponseVO logout() throws CommonServiceException, ParamErrorException {

        String userId = TraceUtil.getUserId();

        /**
         * 1、用户信息放入redis缓存
         * 2、去掉用户缓存
         */


        return BaseResponseVO.success();
    }
}
