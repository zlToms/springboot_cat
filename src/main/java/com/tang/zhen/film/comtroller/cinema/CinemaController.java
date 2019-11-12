package com.tang.zhen.film.comtroller.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tang.zhen.film.comtroller.cinema.vo.*;
import com.tang.zhen.film.comtroller.cinema.vo.condition.AreaResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.BrandResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.HallTypeResVO;
import com.tang.zhen.film.comtroller.cinema.vo.request.DescribeCinemaRequestVO;
import com.tang.zhen.film.comtroller.cinema.vo.response.FieldInfoRequestVO;
import com.tang.zhen.film.comtroller.common.BaseResponseVO;
import com.tang.zhen.film.config.properties.FilmProperties;
import com.tang.zhen.film.service.cinema.CinemaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cinema")
public class CinemaController {

    @Autowired
    private FilmProperties filmProperties;

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;


    @RequestMapping(value = "/getFields",method = RequestMethod.GET)
    public BaseResponseVO getFields(String cinemaId){

        //获取元数据
        CinemaDetailVO vo = cinemaServiceAPI.describeCinemaDetails(cinemaId);
        List<CinemaFilmVO> filmVOS = cinemaServiceAPI.describeFieldsAndFilmInfo(cinemaId);

        //组织返回参数
        Map<String,Object> result = Maps.newHashMap();
        result.put("cinemaInfo",vo);
        List<Map<String,CinemaFilmVO>> filmList = Lists.newArrayList();
        filmVOS.stream().forEach((film)->{
            Map<String,CinemaFilmVO> filmVOMap =Maps.newHashMap();
            filmVOMap.put("filmInfo",film);
            filmList.add(filmVOMap);
        });
        result.put("filmList",filmList);

        return  BaseResponseVO.success(filmProperties.getImgPre(),result);
    }


    @RequestMapping(value = "/getFieldInfo",method = RequestMethod.POST)
    public BaseResponseVO getFieldInfo(@RequestBody FieldInfoRequestVO requestVO){

        //获取元数据
        CinemaDetailVO vo = cinemaServiceAPI.describeCinemaDetails(requestVO.getCinemaId());
        FieldHallInfoVO hallInfoVO = cinemaServiceAPI.describeHallInfoByFieldId(requestVO.getFieldId());
        CinemaFilmInfoVO filmInfoVO = cinemaServiceAPI.describeFilmInfoByFieldId(requestVO.getFieldId());

        //组织返回参数
        Map<String,Object> result = Maps.newHashMap();
        result.put("filmInfo",filmInfoVO);
        result.put("cinemaInfo",vo);
        result.put("hallInfo",hallInfoVO);

        return  BaseResponseVO.success(filmProperties.getImgPre(),result);
    }


    @RequestMapping(value = "/getCinemas",method = RequestMethod.GET)
    public BaseResponseVO getCinemas( DescribeCinemaRequestVO requestVO){

        //获取元数据
        Page<CinemaVO> cinemaVOPage = cinemaServiceAPI.describeCinemaInfo(requestVO);

        //组织返回参数
        Map<String,Object> result = Maps.newHashMap();
        result.put("filmInfo",cinemaVOPage.getRecords());
        return  BaseResponseVO.success(cinemaVOPage.getCurrent(),cinemaVOPage.getPages(),filmProperties.getImgPre(),result);
    }


    @RequestMapping(value = "/getCondition",method = RequestMethod.GET)
    public BaseResponseVO getCondition(@RequestParam(name = "brandId",required = false,defaultValue = "99") Integer brandId,
                                       @RequestParam(name = "hallType",required = false,defaultValue = "99") Integer hallType,
                                       @RequestParam(name = "areaId",required = false,defaultValue = "99") Integer areaId){

        //验证ID是否有效
       if(!cinemaServiceAPI.checkCondition(brandId,"brand")){
           brandId=99;
       }
        if(!cinemaServiceAPI.checkCondition(areaId,"area")){
            areaId=99;
        }
        if(!cinemaServiceAPI.checkCondition(hallType,"hallType")){
            hallType=99;
        }

        //获取结果
        List<BrandResVO> brandResVOS = cinemaServiceAPI.describeBrandConditions(brandId);
        List<AreaResVO> areaResVOS = cinemaServiceAPI.describeAreaConditions(areaId);
        List<HallTypeResVO> hallTypeResVOS = cinemaServiceAPI.describeHallTypeConditions(hallType);
        //组织返回参数
        Map<String,Object> result = Maps.newHashMap();
        result.put("brandList",brandResVOS);
        result.put("areaList",areaResVOS);
        result.put("halltypeList",hallTypeResVOS);
        return  BaseResponseVO.success(result);
    }



}
