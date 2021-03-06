package com.tang.zhen.film.comtroller.film;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.tang.zhen.film.comtroller.common.BaseResponseVO;
import com.tang.zhen.film.comtroller.film.vo.request.DescribeFilmListReqVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.CatInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.SourceInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.condition.YearInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.ActorResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.FilmDetailResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.ImagesResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.BannerInfoResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.HotFilmListResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.RankFilmListResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.index.SoonFilmListResultVO;
import com.tang.zhen.film.config.properties.FilmProperties;
import com.tang.zhen.film.dao.entity.FilmInfoT;
import com.tang.zhen.film.service.common.CommonServiceException;
import com.tang.zhen.film.service.film.FilmServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmServiceAPI filmServiceAPI;

    @Autowired
    private FilmProperties filmProperties;

    /*
       获取首页信息
     */
    @RequestMapping(value = "/getIndex",method = RequestMethod.GET)
    public BaseResponseVO describeIndex() throws CommonServiceException {

        Map<String,Object> resultMap = Maps.newHashMap();
        //获取banners
        List<BannerInfoResultVO> banners = filmServiceAPI.describeBanners();
        resultMap.put("banners",banners);
        
        //获取 hotFilms
        List<HotFilmListResultVO> hotFilms = filmServiceAPI.describeHotFilms();
        int hotFilmNum = filmServiceAPI.describeIndexFilmNum("1");

        Map<String,Object> hotFilmMap = Maps.newHashMap();
        hotFilmMap.put("filmInfo",hotFilms);
        hotFilmMap.put("filmNum",hotFilmNum);

        resultMap.put("hotFilms",hotFilmMap);

        //获取 soonFilms
        List<SoonFilmListResultVO> soonFilms = filmServiceAPI.describeSoonFilms();
        int soonFilmNum = filmServiceAPI.describeIndexFilmNum("2");

        Map<String,Object> soonFilmMap = Maps.newHashMap();
        soonFilmMap.put("filmInfo",soonFilms);
        soonFilmMap.put("filmNum",soonFilmNum);

        resultMap.put("soonFilms",soonFilmMap);

        //boxRanking
        List<RankFilmListResultVO> boxRankFilms = filmServiceAPI.boxRankFilmList();
        resultMap.put("boxRanking",boxRankFilms);
        //exceptRanking
        List<RankFilmListResultVO> exceptRankFilms = filmServiceAPI.exceptRankFilmList();
        resultMap.put("exceptRanking",exceptRankFilms);
        //top100
        List<RankFilmListResultVO> topRankFilms = filmServiceAPI.topRankFilmList();
        resultMap.put("top100",topRankFilms);
        return BaseResponseVO.success(filmProperties.getImgPre(),resultMap);
    }

    /*
         获取查询条件列表
     */
    @RequestMapping(value = "/getConditionList",method = RequestMethod.GET)
    public BaseResponseVO getConditionList(
            @RequestParam(name ="catId",required = false ,defaultValue = "99") String catId,
            @RequestParam(name ="sourceId",required = false ,defaultValue = "99")String sourceId,
            @RequestParam(name ="yearId",required = false ,defaultValue = "99")String yearId
    ) throws CommonServiceException {

        //检查参数
        catId = filmServiceAPI.checkCondition(catId,"cat");
        sourceId = filmServiceAPI.checkCondition(sourceId,"source");
        yearId = filmServiceAPI.checkCondition(yearId,"year");

        Map<String,Object> resultMap = Maps.newHashMap();
        List<CatInfoResultVO> catInfoResultVOS = filmServiceAPI.describeCatInfos(catId);
        List<SourceInfoResultVO> resultVOS = filmServiceAPI.describeSourceInfos(sourceId);
        List<YearInfoResultVO> resultVOS1 = filmServiceAPI.describeYearInfos(yearId);

        resultMap.put("catInfo",catInfoResultVOS);
        resultMap.put("sourceInfo",resultVOS);
        resultMap.put("yearInfo",resultVOS1);
        return BaseResponseVO.success(resultMap);
    }

    /*
        获取电影列表信息
     */
    @RequestMapping(value = "/getFilms",method = RequestMethod.GET)
    public BaseResponseVO getFilms(DescribeFilmListReqVO requestVO) throws CommonServiceException {

        IPage<FilmInfoT> page = filmServiceAPI.describeFilms(requestVO);

        return BaseResponseVO.success(page.getCurrent(),page.getPages(),filmProperties.getImgPre(),page.getRecords());
    }


    /*
        获取电影详情
     */
    @RequestMapping(value = "/films/{searchStr}",method = RequestMethod.GET)
    public BaseResponseVO describeFilmDetails(@PathVariable(name = "searchStr") String searchStr, String searchType) throws CommonServiceException {

        //g根据查询条件获取电影编号
        FilmDetailResultVO filmDetailResultVO = filmServiceAPI.describeFilmDetails(searchStr, searchType);

        //biography
        String biography = filmServiceAPI.describeFilmBiography(filmDetailResultVO.getFilmId());

        //actors
        Map<String,Object> actors = Maps.newHashMap();

        ActorResultVO director = filmServiceAPI.describeDiestor(filmDetailResultVO.getFilmId());
        List<ActorResultVO> actorResult = filmServiceAPI.describeActors(filmDetailResultVO.getFilmId());

        actors.put("director",director);
        actors.put("actors",actorResult);

        //imgs
        ImagesResultVO imagesResultVO = filmServiceAPI.describeFilmImages(filmDetailResultVO.getFilmId());

        filmDetailResultVO.getInfo04().put("biography",biography);
        filmDetailResultVO.getInfo04().put("actors",actors);

        filmDetailResultVO.setImgs(imagesResultVO);
        return BaseResponseVO.success(filmProperties.getImgPre(),filmDetailResultVO);
    }



}
