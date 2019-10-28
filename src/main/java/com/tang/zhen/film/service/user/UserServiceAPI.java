package com.tang.zhen.film.service.user;

import com.tang.zhen.film.comtroller.user.vo.EnrollUserVO;
import com.tang.zhen.film.comtroller.user.vo.UserInfoVO;
import com.tang.zhen.film.service.common.CommonServiceException;

public interface UserServiceAPI {
    /**
     * 用户登记接口
     * @param enrollUserVO
     * @throws CommonServiceException
     */
    void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException;

    /**
     * 验证用户名是否存在
     * @param userName
     * @return
     * @throws CommonServiceException
     */
    boolean checkUserName(String userName) throws  CommonServiceException;

    /**
     * 用户名密码验证
     * @param userName
     * @param userPwd
     * @return
     * @throws CommonServiceException
     */
    boolean userAuth(String userName, String userPwd) throws CommonServiceException;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfoVO  describeUserInfo(String userId)throws CommonServiceException;

    /**
     * 修改用户信息
     * @param userInfoVO
     * @return
     */
    UserInfoVO  updateUserInfo(UserInfoVO userInfoVO)throws CommonServiceException;
}
