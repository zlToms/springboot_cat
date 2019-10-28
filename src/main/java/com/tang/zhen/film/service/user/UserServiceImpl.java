package com.tang.zhen.film.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tang.zhen.film.common.utils.MD5Util;
import com.tang.zhen.film.common.utils.ToolUtils;
import com.tang.zhen.film.comtroller.user.vo.EnrollUserVO;
import com.tang.zhen.film.comtroller.user.vo.UserInfoVO;
import com.tang.zhen.film.dao.entity.NextUserT;
import com.tang.zhen.film.dao.mapper.NextUserTMapper;
import com.tang.zhen.film.service.common.CommonServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements  UserServiceAPI {

    @Autowired
    private NextUserTMapper userTMapper;
    @Override
    public void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException {

        //检验

        //EnrollUserVO -> NextUserT
        NextUserT nextUserT = new NextUserT();

        BeanUtils.copyProperties(enrollUserVO,nextUserT);
        nextUserT.setUserName(enrollUserVO.getUsername());
        nextUserT.setUserPwd(MD5Util.encrypt(enrollUserVO.getPassword()));

        //数据插入
        int isSuccess =userTMapper.insert(nextUserT);

        //判断数据是否插入为空
        if(isSuccess!=1){
            throw  new CommonServiceException(501,"用户登记失败");
        }

    }

    @Override
    public boolean checkUserName(String userName) throws CommonServiceException {
       // Optional.ofNullable(userName).orElseThrow(new CommonServiceException(502,"用户名为空"));

        QueryWrapper queryWrapper  = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        Integer hasUserName = userTMapper.selectCount(queryWrapper);

        return hasUserName == 0?false:true;
    }

    @Override
    public boolean userAuth(String userName, String userPwd) throws CommonServiceException {
        if(ToolUtils.isEmpty(userName)||ToolUtils.isEmpty(userPwd)){
            throw  new CommonServiceException(401,"用户登记失败");
        }

        QueryWrapper queryWrapper  = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        //判断用户名是否存在
        List<NextUserT> list = userTMapper.selectList(queryWrapper);
        if(list.size()==0){
            return false;
        }else{
            //如果存在判断密码是否正确
            NextUserT nextUserT = list.get(0);

            // 对用户密码进行MD5加密。然后判断两个密码是否相等
            if(MD5Util.encrypt(userPwd).equals(nextUserT.getUserPwd())){
                return true;
            }
        }

        return false;
    }

    @Override
    public UserInfoVO describeUserInfo(String userId) throws CommonServiceException {
        NextUserT nextUserT = userTMapper.selectById(userId);
        if(nextUserT!= null && nextUserT.getUuid()!= null){
            UserInfoVO userInfoVO = user2infoVO(nextUserT);
            return userInfoVO;
        }else{
            throw new CommonServiceException(404,"用户编号为["+ userId+"]的用户不存在");
        }
    }

    @Override
    public UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException {

        NextUserT nextUserT = info2user(userInfoVO);
        if(nextUserT!= null && nextUserT.getUuid()!= null){
            int isSuccess = userTMapper.updateById(nextUserT);
            if(isSuccess == 1){
                UserInfoVO result = describeUserInfo(userInfoVO.getUuid()+"");
                return result;
            }else{
                throw new CommonServiceException(500,"用户信息修改失败");
            }
        }else{
            throw new CommonServiceException(404,"用户编号为["+ userInfoVO.getUuid()+"]的用户不存在");
        }

    }



    /**
    -----------------------------以下都是自定义----------------------------------------------------------------
     */

    private  UserInfoVO user2infoVO(NextUserT nextUserT ){
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(nextUserT,userInfoVO);
        userInfoVO.setUsername(nextUserT.getUserName());
        userInfoVO.setNickname(nextUserT.getNickName());

        userInfoVO.setBeginTime(nextUserT.getBeginTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVO.setUpdateTime(nextUserT.getUpdateTime().toEpochSecond(ZoneOffset.of("+8")));

        userInfoVO.setLifeState(nextUserT.getLifeState()+"");
        return userInfoVO;
    }

    private  NextUserT info2user(UserInfoVO userInfoVO ){
        NextUserT nextUserT = new NextUserT();
        BeanUtils.copyProperties(userInfoVO,nextUserT);


        nextUserT.setUserName(userInfoVO.getUsername());
        nextUserT.setNickName(userInfoVO.getNickname());


        nextUserT.setUpdateTime(LocalDateTime.now());


        //最好是使用正则表达式判断是否为数字类型再转换
        nextUserT.setLifeState(Integer.parseInt(userInfoVO.getLifeState()));
        return nextUserT;
    }
}
