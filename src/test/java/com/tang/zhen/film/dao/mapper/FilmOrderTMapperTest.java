package com.tang.zhen.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.zhen.film.comtroller.order.vo.response.OrderDetailResVO;
import com.tang.zhen.film.dao.entity.FilmOrderT;
import com.tang.zhen.film.service.order.bo.OrderPriceBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmOrderTMapperTest {

    @Autowired
    private FilmOrderTMapper filmOrderTMapper;

    @Test
    public void describeOrderDetailsById() {
        String id = "415sdf58ew12ds5fe1";
        OrderDetailResVO vo = filmOrderTMapper.describeOrderDetailsById(id);
        System.out.println(vo);
    }

    @Test
    public void describeOrderDtailsByUser() {
        String userId = "1";
        Page<FilmOrderT> page = new Page<>(1,10);
        IPage<OrderDetailResVO> page1 = filmOrderTMapper.describeOrderDtailsByUser(page, userId);
        List<OrderDetailResVO> records = page1.getRecords();
        records.stream().forEach(
                System.out::println
        );

    }

    @Test
    public void describeOrderPriceByFieldId() {
        OrderPriceBO bo = filmOrderTMapper.describeOrderPriceByFieldId("1");
        System.out.println(bo);
    }

    @Test
    public void describeSoldSeats() {
        String s = filmOrderTMapper.describeSoldSeats("1");
        System.out.println(s);
    }
}