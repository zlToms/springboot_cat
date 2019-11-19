package com.tang.zhen.film.service.film;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tang.zhen.film.comtroller.film.vo.request.DescribeFilmListReqVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.CatInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.SourceInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.YearInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.FilmDetailResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.BannerInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.HotFilmListResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.RankFilmListResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.SoonFilmListResultVO;
import com.tang.zhen.film.dao.entity.FilmInfoT;
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
        try {
            List<HotFilmListResultVO> list = filmServiceAPI.describeHotFilms();
            list.stream().forEach(
                    System.out::println
            );
        } catch (CommonServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void describeSoonFilms() throws CommonServiceException {
        List<SoonFilmListResultVO> vos = filmServiceAPI.describeSoonFilms();
        vos.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void boxRankFilmList() throws CommonServiceException {
        List<RankFilmListResultVO> vos = filmServiceAPI.boxRankFilmList();
        vos.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void exceptRankFilmList() throws CommonServiceException {
        List<RankFilmListResultVO> vos = filmServiceAPI.exceptRankFilmList();
        vos.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void topRankFilmList() throws CommonServiceException {
        List<RankFilmListResultVO> vos = filmServiceAPI.topRankFilmList();
        vos.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void checkCondition() {
    }

    @Test
    public void describeCatInfos() throws CommonServiceException {
        List<CatInfoResultVO> vos = filmServiceAPI.describeCatInfos("1");
        vos.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void describeSourceInfos() throws CommonServiceException {
        List<SourceInfoResultVO> vos = filmServiceAPI.describeSourceInfos("1");
        vos.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void describeYearInfos() throws CommonServiceException {
        List<YearInfoResultVO> vos = filmServiceAPI.describeYearInfos("1");
        vos.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void describeFilms() throws CommonServiceException {
        DescribeFilmListReqVO describeFilmListReqVO = new DescribeFilmListReqVO();
        IPage<FilmInfoT> filmInfoTIPage = filmServiceAPI.describeFilms(describeFilmListReqVO);
        System.out.println(filmInfoTIPage.getCurrent()+","+filmInfoTIPage.getPages()+","
        +filmInfoTIPage.getTotal());
    }

    @Test
    public void describeFilmDetails() throws CommonServiceException {
        String searchStr  ="药神";
        String searchType= "1";
        FilmDetailResultVO vo = filmServiceAPI.describeFilmDetails(searchStr, searchType);
        System.out.println(vo);
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