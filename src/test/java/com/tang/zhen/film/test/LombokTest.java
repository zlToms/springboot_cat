package com.tang.zhen.film.test;

import com.tang.zhen.film.example.service.bo.RegisterUserBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LombokTest {

    @Test
    public  void test2(){
        RegisterUserBO userBO = RegisterUserBO.builder()
                                .uuid("001")
                                .userName("朱文帅")
                                .userPwd("123456")
                                .build();
        System.out.println(userBO);
        log.info("bo ="+userBO);
    }


    public  void test(){
       /* RegisterUserBO userBO = new RegisterUserBO();
        userBO.setUserName("朱文帅");
        userBO.setUuid("003");
        userBO.setUserPwd("123456");
        System.out.println(userBO);*/
    }
}
