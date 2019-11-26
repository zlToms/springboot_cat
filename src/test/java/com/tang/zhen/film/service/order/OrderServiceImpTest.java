package com.tang.zhen.film.service.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tang.zhen.film.comtroller.order.vo.response.OrderDetailResVO;
import com.tang.zhen.film.service.common.CommonServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImpTest {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @Test
    public void checkSeats() throws IOException, CommonServiceException {
        String fieldId = "1";
        String seats = "1,3,5,0";
        orderServiceImp.checkSeats(fieldId,seats);
    }

    @Test
    public void checkSoldSeats() throws CommonServiceException {
        String fieldId = "1";
        String seats = "1,3,0";
        orderServiceImp.checkSoldSeats(fieldId,seats);
    }

    @Test
    public void describeOrderInfoByUser() throws CommonServiceException {
        String userId = "1";
        int nowPage = 1;
        int pageSize = 10;
        IPage<OrderDetailResVO> iPage = orderServiceImp.describeOrderInfoByUser(nowPage, pageSize, userId);
        iPage.getRecords().stream().forEach(
                System.out::println
        );
    }

    @Test
    public void saveOrder() {

    }


}