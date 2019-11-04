package com.tang.zhen.film.dao.mapper;


import com.tang.zhen.film.comtroller.cinema.vo.CinemaFilmInfoVO;
import com.tang.zhen.film.comtroller.cinema.vo.CinemaFilmVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmFieldTMapperTest {

    @Autowired
    private FilmFieldTMapper filmFieldTMapper;

    @Test
    public void describeFieldListTest(){
        List<CinemaFilmVO> cinemaFilmVOS = filmFieldTMapper.describeFieldList("1");
        System.out.println(cinemaFilmVOS);

    }

    @Test
    public void describeFilmInfoTest(){
        CinemaFilmInfoVO cinemaFilmVOS = filmFieldTMapper.describeFilmInfo("1");
        System.out.println(cinemaFilmVOS);

    }

}