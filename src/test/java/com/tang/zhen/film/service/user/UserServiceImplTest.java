package com.tang.zhen.film.service.user;

import com.tang.zhen.film.comtroller.user.vo.EnrollUserVO;
import com.tang.zhen.film.service.common.CommonServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @Test
    public void userEnroll() throws CommonServiceException {
        EnrollUserVO enrollUserVO = new EnrollUserVO();
        enrollUserVO.setUsername("123");
        enrollUserVO.setPassword("12");
        enrollUserVO.setAddress("1");
        enrollUserVO.setEmail("2");
        enrollUserVO.setPhone("123456");

        userServiceAPI.userEnroll(enrollUserVO);
    }

    @Test
    public void checkUserName() {
    }

    @Test
    public void userAuth() {
    }

    @Test
    public void describeUserInfo() throws CommonServiceException {
        String userId = "5";
        System.out.println(userServiceAPI.describeUserInfo(userId));
    }

    @Test
    public void updateUserInfo() {
    }
}