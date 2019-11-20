package com.tang.zhen.film.service.film;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
import com.tang.zhen.film.dao.entity.FilmBrandDictT;
import com.tang.zhen.film.dao.entity.FilmInfoT;
import com.tang.zhen.film.service.common.CommonServiceException;

import java.util.List;

public interface FilmServiceAPI {

    /*
        Banner信息获取
     */
    List<BannerInfoResultVO>  describeBanners() throws CommonServiceException;

    /*
         获取热映影片
     */
    List<HotFilmListResultVO> describeHotFilms() throws CommonServiceException;

    /*
          获取即将上映的影片
     */
    List<SoonFilmListResultVO> describeSoonFilms() throws CommonServiceException;


    /*
       获取热映或即将上映的影片数量
       filmType 1 表示热映 2 表示即将上映
     */
    int describeIndexFilmNum(String filmType)throws CommonServiceException;
    /*
          票房排行
     */
    List<RankFilmListResultVO> boxRankFilmList()throws CommonServiceException;

    /*
         期待排行
    */
    List<RankFilmListResultVO> exceptRankFilmList() throws CommonServiceException;

    /*
         top100 排行
    */
    List<RankFilmListResultVO> topRankFilmList() throws CommonServiceException;


    /*
       验证有效性
     */
    String checkCondition(String  conditionId,String type) throws CommonServiceException;


    /*
       三种条件查询
     */
    List<CatInfoResultVO> describeCatInfos(String catId) throws CommonServiceException;
    List<SourceInfoResultVO> describeSourceInfos(String sourceId) throws CommonServiceException;
    List<YearInfoResultVO> describeYearInfos(String yearId) throws CommonServiceException;

    /*
       根据条件，获取对应的电影列表的信息
     */
    IPage<FilmInfoT> describeFilms(DescribeFilmListReqVO filmListReqVO)throws CommonServiceException;

     /*
       获取电影的详情
       searchType  -> 0 - 按编号查找 ，1-按名称模糊匹配
     */
     FilmDetailResultVO describeFilmDetails(String searchStr, String searchType) throws CommonServiceException;

     /*
       获取电影的描述信息
      */
     String describeFilmBiography(String filmId) throws CommonServiceException;

     /*
       获取影片图片信息
      */
     ImagesResultVO describeFilmImages(String filmId) throws CommonServiceException;

      /*
        获取导演信息
      */
      ActorResultVO describeDiestor(String filmId) throws CommonServiceException;



      /*
        获取演员信息
      */
      List<ActorResultVO> describeActors(String filmId) throws CommonServiceException;
}
