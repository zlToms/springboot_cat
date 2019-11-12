package com.tang.zhen.film.service.film;

import com.tang.zhen.film.comtroller.film.vo.request.DescribeFilmListReqVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.CatInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.SourceInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.YearInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.ActorResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.FilmDetailResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.ImagesResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.films.DescribeFilmListResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.BannerInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.HotFilmListResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.RankFilmListResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.SoonFilmListResultVO;
import com.tang.zhen.film.dao.mapper.*;
import com.tang.zhen.film.service.common.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements  FilmServiceAPI {
    @Autowired
    private FilmSourceDictTMapper sourceDictTMapper;
    @Autowired
    private FilmYearDictTMapper yearDictTMapper;
    @Autowired
    private FilmCatDictTMapper  catDictTMapper;

    @Autowired
    private FilmBrandDictTMapper brandDictTMapper;

    @Autowired
    private FilmInfoTMapper filmInfoTMapper;
    @Autowired
    private FilmDetailTMapper filmDetailTMapper;

    @Autowired
    private FilmActorTMapper  actorTMapper;
    @Autowired
    private FilmActorRelaTMapper actorRelaTMapper;

    @Override
    public List<BannerInfoResultVO> describeBanners() throws CommonServiceException {
        return null;
    }

    @Override
    public List<HotFilmListResultVO> describeHotFilms() throws CommonServiceException {
        return null;
    }

    @Override
    public List<SoonFilmListResultVO> describeSoonFilms() throws CommonServiceException {
        return null;
    }

    @Override
    public List<RankFilmListResultVO> boxRankFilmList() throws CommonServiceException {
        return null;
    }

    @Override
    public List<RankFilmListResultVO> exceptRankFilmList() throws CommonServiceException {
        return null;
    }

    @Override
    public List<RankFilmListResultVO> topRankFilmList() throws CommonServiceException {
        return null;
    }

    @Override
    public String checkCondition(String conditionId, String type) throws CommonServiceException {
        return null;
    }

    @Override
    public List<CatInfoResultVO> describeCatInfos(String catId) throws CommonServiceException {
        return null;
    }

    @Override
    public List<SourceInfoResultVO> describeSourceInfos(String sourceId) throws CommonServiceException {
        return null;
    }

    @Override
    public List<YearInfoResultVO> describeYearInfos(String yearId) throws CommonServiceException {
        return null;
    }

    @Override
    public List<DescribeFilmListResultVO> describeFilms(DescribeFilmListReqVO filmListReqVO) throws CommonServiceException {
        return null;
    }

    @Override
    public FilmDetailResultVO describeFilmDetails(String searchStr, String searchType) throws CommonServiceException {
        return null;
    }

    @Override
    public String describeFilmBio(String filmId) throws CommonServiceException {
        return null;
    }

    @Override
    public ImagesResultVO describeFilmImages(String filmId) throws CommonServiceException {
        return null;
    }

    @Override
    public ActorResultVO describeDiestor(String filmId) throws CommonServiceException {
        return null;
    }

    @Override
    public List<ActorResultVO> describeActors(String filmId) throws CommonServiceException {
        return null;
    }
}
