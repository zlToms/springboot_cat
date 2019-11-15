package com.tang.zhen.film.service.film;

import com.tang.zhen.film.comtroller.film.vo.response.index.BannerInfoResultVO;
import com.tang.zhen.film.service.common.CommonServiceException;
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
public class FilmServiceImplTest {

    @Autowired
    private FilmServiceAPI filmServiceAPI;

    @Test
    public void describeBanners() throws CommonServiceException {
        List<BannerInfoResultVO> resultVOS = filmServiceAPI.describeBanners();
        resultVOS.stream().forEach((data)->System.out.println(data));
    }

    @Test
    public void describeHotFilms() {
    }

    @Test
    public void describeSoonFilms() {
    }

    @Test
    public void boxRankFilmList() {
    }

    @Test
    public void exceptRankFilmList() {
    }

    @Test
    public void topRankFilmList() {
    }

    @Test
    public void checkCondition() {
    }

    @Test
    public void describeCatInfos() {
    }

    @Test
    public void describeSourceInfos() {
    }

    @Test
    public void describeYearInfos() {
    }

    @Test
    public void describeFilms() {
    }

    @Test
    public void describeFilmDetails() {
    }

    @Test
    public void describeFilmBio() {
    }

    @Test
    public void describeFilmImages() {
    }

    @Test
    public void describeDiestor() {
    }

    @Test
    public void describeActors() {
    }
}