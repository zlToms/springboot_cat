package com.tang.zhen.film.service.film;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.zhen.film.common.utils.ToolUtils;
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
import com.tang.zhen.film.dao.entity.*;
import com.tang.zhen.film.dao.mapper.*;
import com.tang.zhen.film.service.common.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private  FilmBannerTMapper bannerTMapper;

    @Override
    public List<BannerInfoResultVO> describeBanners() throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_valid","0");

        List<FilmBannerT> list = bannerTMapper.selectList(queryWrapper);

        List<BannerInfoResultVO> result = new ArrayList<>();
        list.parallelStream().forEach((banner)->{
            BannerInfoResultVO bannerInfoResultVO = new BannerInfoResultVO();
            bannerInfoResultVO.setBannerAddress(banner.getBannerAddress());
            bannerInfoResultVO.setBannerId(banner.getUuid()+"");
            bannerInfoResultVO.setBannerUrl(banner.getBannerUrl());
            result.add(bannerInfoResultVO);
        });
        return result;
    }

    @Override
    public List<HotFilmListResultVO> describeHotFilms() throws CommonServiceException {
        //默认热映的影片在首页中只查看八条
        Page<FilmInfoT> page = new Page<>(1,8);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status","1");

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);
        List<HotFilmListResultVO> results = new ArrayList<>();
        iPage.getRecords().stream().forEach((film)->{
            HotFilmListResultVO result = new HotFilmListResultVO();
            result.setFilmId(film.getUuid()+"");
            result.setFilmName(film.getFilmName());
            result.setFilmScore(film.getFilmScore());
            result.setFilmType(film.getFilmType()+"");
            result.setIngAdress(film.getImgAddress());
            results.add(result);
        });


        return results;
    }

    @Override
    public List<SoonFilmListResultVO> describeSoonFilms() throws CommonServiceException {

        //默认即将上映的影片在首页中只查看八条
        Page<FilmInfoT> page = new Page<>(1,8);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status","2");

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);
        List<SoonFilmListResultVO>results = new ArrayList<>();
        iPage.getRecords().stream().forEach((film)->{
            SoonFilmListResultVO result = new SoonFilmListResultVO();
            result.setIngAdress(film.getImgAddress());
            result.setFilmType(film.getFilmType()+"");
            result.setFilmName(film.getFilmName());
            result.setFilmId(film.getUuid()+"");
            result.setExpectNum(film.getFilmPresalenum()+"");
            result.setShowTime(localTime2String(film.getFilmTime()));

            results.add(result);
        });
        return results;
    }

    @Override
    public int describeIndexFilmNum(String filmType) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        if("2".equals(filmType)){
            queryWrapper.eq("film_status","2");
        }else{
            queryWrapper.eq("film_status","1");
        }
        Integer integer = filmInfoTMapper.selectCount(queryWrapper);
        return integer;
    }

    private String localTime2String(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDateTime);
    }

    /**
     * 票房排行 - 正在热映的电影  top10
     */
    @Override
    public List<RankFilmListResultVO> boxRankFilmList() throws CommonServiceException {
        Page<FilmInfoT> page = new Page<>(1,10);
        page.setDesc("film_box_office");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status","1");
        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);
        List<RankFilmListResultVO> results = new ArrayList<>();
        iPage.getRecords().stream().forEach((film)->{
            RankFilmListResultVO vo = new RankFilmListResultVO();
            vo.setScore(film.getFilmScore());
            vo.setImgAddress(film.getImgAddress());
            vo.setFilmName(film.getFilmName());
            vo.setFilmId(film.getUuid()+"");
            vo.setExceptNum(film.getFilmPresalenum()+"");
            vo.setBoxNum(film.getFilmBoxOffice()+"");
            results.add(vo);
        });

        return results;
    }

    /**
     * 期待观影人数排行 - 即将上映的电影
     */
    @Override
    public List<RankFilmListResultVO> exceptRankFilmList() throws CommonServiceException {
        Page<FilmInfoT> page = new Page<>(1,10);
        page.setDesc("film_preSaleNum");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status","2");
        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);
        List<RankFilmListResultVO> results = new ArrayList<>();
        iPage.getRecords().stream().forEach((film)->{
            RankFilmListResultVO vo = new RankFilmListResultVO();
            vo.setScore(film.getFilmScore());
            vo.setImgAddress(film.getImgAddress());
            vo.setFilmName(film.getFilmName());
            vo.setFilmId(film.getUuid()+"");
            vo.setExceptNum(film.getFilmPresalenum()+"");
            vo.setBoxNum(film.getFilmBoxOffice()+"");
            results.add(vo);
        });

        return results;
    }

    /**
     * 评分排行 - 正在热映的电影
     */
    @Override
    public List<RankFilmListResultVO> topRankFilmList() throws CommonServiceException {
        Page<FilmInfoT> page = new Page<>(1,10);
        page.setDesc("film_score");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status","1");
        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);
        List<RankFilmListResultVO> results = new ArrayList<>();
        iPage.getRecords().stream().forEach((film)->{
            RankFilmListResultVO vo = new RankFilmListResultVO();
            vo.setScore(film.getFilmScore());
            vo.setImgAddress(film.getImgAddress());
            vo.setFilmName(film.getFilmName());
            vo.setFilmId(film.getUuid()+"");
            vo.setExceptNum(film.getFilmPresalenum()+"");
            vo.setBoxNum(film.getFilmBoxOffice()+"");
            results.add(vo);
        });

        return results;
    }

    @Override
    public String checkCondition(String conditionId, String type) throws CommonServiceException {
        switch (type){
            case "source":
                if("99".equals(conditionId)){
                    return conditionId;
                }
                FilmSourceDictT dictT = sourceDictTMapper.selectById(conditionId);
                if(dictT!=null&& ToolUtils.isNotEmpty(dictT.getUuid()+"")){
                    return conditionId;
                }else{
                    return "99";
                }
            case "year":
                if("99".equals(conditionId)){
                    return conditionId;
                }
                FilmYearDictT dictT1 = yearDictTMapper.selectById(conditionId);
                if(dictT1!=null&& ToolUtils.isNotEmpty(dictT1.getUuid()+"")){
                    return conditionId;
                }else{
                    return "99";
                }
            case "cat":
                if("99".equals(conditionId)){
                    return conditionId;
                }
                FilmCatDictT dictT2 = catDictTMapper.selectById(conditionId);
                if(dictT2!=null&& ToolUtils.isNotEmpty(dictT2.getUuid()+"")){
                    return conditionId;
                }else{
                    return "99";
                }
            default:
                throw new CommonServiceException(404,"ivia;id conditionType");
        }
    }

    @Override
    public List<CatInfoResultVO> describeCatInfos(String catId) throws CommonServiceException {
        List<FilmCatDictT> list = catDictTMapper.selectList(null);
        List<CatInfoResultVO> result = list.stream().map((data)->{
            CatInfoResultVO catInfoResultVO = new CatInfoResultVO();
            catInfoResultVO.setCartId(data.getUuid()+"");
            catInfoResultVO.setCartName(data.getShowName());
            if(catId.equals(data.getUuid()+"")){
                catInfoResultVO.setIsActive("true");
            }else{
                catInfoResultVO.setIsActive("false");
            }
            return catInfoResultVO;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SourceInfoResultVO> describeSourceInfos(String sourceId) throws CommonServiceException {
        List<FilmSourceDictT> list = sourceDictTMapper.selectList(null);
        List<SourceInfoResultVO> resultVOS = list.stream().map((data)->{
            SourceInfoResultVO sourceInfoResultVO = new SourceInfoResultVO();
            sourceInfoResultVO.setSourceId(data.getUuid()+"");
            sourceInfoResultVO.setSourceName(data.getShowName());
            if(sourceId.equals(data.getUuid()+"")){
                sourceInfoResultVO.setIsActive("true");
            }else{
                sourceInfoResultVO.setIsActive("false");
            }
            return sourceInfoResultVO;
        }).collect(Collectors.toList());
        return resultVOS;
    }

    @Override
    public List<YearInfoResultVO> describeYearInfos(String yearId) throws CommonServiceException {
        List<FilmYearDictT> list = yearDictTMapper.selectList(null);
        List<YearInfoResultVO> resultVOS = list.stream().map((data)->{
            YearInfoResultVO yearInfoResultVO = new YearInfoResultVO();
            yearInfoResultVO.setYearId(data.getUuid()+"");
            yearInfoResultVO.setYearName(data.getShowName());
            if(yearId.equals(data.getUuid()+"")){
                yearInfoResultVO.setIsActive("true");
            }else{
                yearInfoResultVO.setIsActive("false");
            }
            return yearInfoResultVO;
        }).collect(Collectors.toList());
        return resultVOS;
    }

    @Override
    public IPage<FilmInfoT> describeFilms(DescribeFilmListReqVO filmListReqVO) throws CommonServiceException {
        Page<FilmInfoT> infoTPage =
                new Page<FilmInfoT>(Long.parseLong(filmListReqVO.getNowPage()),Long.parseLong(filmListReqVO.getPageSize()));


        //排序方式  1- 按热门搜索   2- 按时间搜索 3- 按评价搜索
        Map<String,String> sortMap = new HashMap<>();
        sortMap.put("1","film_preSaleNum");
        sortMap.put("2","film_time");
        sortMap.put("3","film_score");
        //hashMap搜索的时间复杂度是log0 远比switch要快

        infoTPage.setDesc(sortMap.get(filmListReqVO.getSortId()));

        QueryWrapper queryWrapper = new QueryWrapper();

        //判断待搜索列表内容  1-正在热映 ， 2-即将上映 ， 3- 经典影片
        queryWrapper.eq("film_status",filmListReqVO.getShowType());
        //组织QueryWrapper的内容
        if(!"99".equals(filmListReqVO.getSortId())){
            queryWrapper.eq("film_source",filmListReqVO.getSortId());
        }
        if(!"99".equals(filmListReqVO.getYearId())){
            queryWrapper.eq("film_date",filmListReqVO.getYearId());
        }
        //#3#2#2
        if(!"99".equals(filmListReqVO.getCatId())){
            queryWrapper.like("film_cats","#" +filmListReqVO.getCatId() +"#");
        }
        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(infoTPage, queryWrapper);

        return iPage;
    }

    @Override
    public FilmDetailResultVO describeFilmDetails(String searchStr, String searchType) throws CommonServiceException {
        FilmDetailResultVO result ;
        //0表示按编号查找， 1表示按名称查找
        if("0".equals(searchType)){
            result = filmInfoTMapper.describeFilmDetailByFilmId(searchStr);
        }else{
            result = filmInfoTMapper.describeFilmDetailByFilmName(searchStr);
        }
        return result;
    }

    @Override
    public String describeFilmBiography(String filmId) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_id",filmId);

        String bipgraphy = "";

        List<FilmDetailT> selectList = filmDetailTMapper.selectList(queryWrapper);
        if(selectList!=null&& selectList.size()>0){
            FilmDetailT detailT = selectList.get(0);
            bipgraphy = detailT.getBiography();
        }
        return bipgraphy;
    }

    @Override
    public ImagesResultVO describeFilmImages(String filmId) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_id",filmId);

        ImagesResultVO imagesResultVO = new ImagesResultVO();

        List<FilmDetailT> selectList = filmDetailTMapper.selectList(queryWrapper);
        if(selectList!=null&& selectList.size()>0){
            FilmDetailT detailT = selectList.get(0);
            String[] imgs = detailT.getFilmImgs().split(",");
            //验证imgs是否存在，是不是4个
            imagesResultVO.setMainImg(imgs[0]);
            imagesResultVO.setImg01(imgs[1]);
            imagesResultVO.setImg02(imgs[2]);
            imagesResultVO.setImg03(imgs[3]);
            imagesResultVO.setImg04(imgs[4]);
        }

        return imagesResultVO;
    }

    @Override
    public ActorResultVO describeDiestor(String filmId) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_id",filmId);

        String directorId = "";
        ActorResultVO actorResultVO = new ActorResultVO();
        //根据filmId获取导演编号
        List<FilmDetailT> selectList = filmDetailTMapper.selectList(queryWrapper);
        if(selectList!=null&& selectList.size()>0){
            FilmDetailT detailT = selectList.get(0);
            directorId = detailT.getDirectorId()+"";
        }

        //根据导演编号获取对应的导演信息
        if(ToolUtils.isNotEmpty(directorId)){
            FilmActorT director = actorTMapper.selectById(directorId);
            actorResultVO.setDirectorName(director.getActorName());
            actorResultVO.setImgAddress(director.getActorImg());
        }

        return actorResultVO;
    }

    @Override
    public List<ActorResultVO> describeActors(String filmId) throws CommonServiceException {
        List<ActorResultVO> actorResultVOS = actorTMapper.describeActorsByFilmId(filmId);
        return actorResultVOS;
    }
}
