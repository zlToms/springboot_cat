package com.tang.zhen.film.service.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.zhen.film.comtroller.cinema.vo.CinemaVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.AreaResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.BrandResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.HallTypeResVO;
import com.tang.zhen.film.comtroller.cinema.vo.request.DescribeCinemaRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaServiceImplTest {

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @Test
    public void describeCinemaInfo() {
        DescribeCinemaRequestVO vo = new DescribeCinemaRequestVO();
        Page<CinemaVO> page = cinemaServiceAPI.describeCinemaInfo(vo);
       log.info("nowPage:{},totalPage:{},recordNum:{},records:{}",page.getCurrent(),
               page.getPages(),page.getTotal(),page.getRecords());
    }

    @Test
    public void describeBrandConditions() {

        List<BrandResVO> brandResVOS = cinemaServiceAPI.describeBrandConditions(1);
        brandResVOS.stream().forEach(
                data ->System.out.println(data)
        );
    }

    @Test
    public void describeAreaConditions() {
        List<AreaResVO> vos = cinemaServiceAPI.describeAreaConditions(1);
        vos.stream().forEach(data->System.out.println(data));
    }

    @Test
    public void describeHallTypeConditions() {
        List<HallTypeResVO> vos = cinemaServiceAPI.describeHallTypeConditions(1);
        vos.stream().forEach(data->System.out.println(data));
    }

    @Test
    public void describeCinemaDetails() {
    }

    @Test
    public void describeFieldsAndFilmInfo() {
    }

    @Test
    public void describeFilmInfoByFieldId() {
    }

    @Test
    public void describeHallInfoByFieldId() {
    }
}