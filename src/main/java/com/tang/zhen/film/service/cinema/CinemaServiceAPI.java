package com.tang.zhen.film.service.cinema;

import com.tang.zhen.film.comtroller.cinema.vo.*;
import com.tang.zhen.film.comtroller.cinema.vo.condition.AreaResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.BrandResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.HallTypeResVO;
import com.tang.zhen.film.comtroller.cinema.vo.request.DescribeCinemaRequestVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CinemaServiceAPI {

    /**
     * 根据条件查询影院列表
     * @param describeCinemaRequestVO
     * @return
     */
    List<CinemaVO>  describeCinemaInfo(DescribeCinemaRequestVO describeCinemaRequestVO);

    /**
     * 获取查询条件
     */
    List<BrandResVO> describeBrandConditions(int brandId);

    List<AreaResVO> describeAreaConditions(int areaId);

    List<HallTypeResVO> describeHallTypeConditions(int hallTypeId);

    /**
     * 根据影院编号获取影院信息
     */
    CinemaDetailVO describeCinemaDetails(String cinemaId);

    /**
     * 根据影院编号获取场次信息
     */
    List<CinemaFilmVO> describeFieldsAndFilmInfo(String cinemaId);

    /**
     * 根据场次编号获取电影信息
     */
    CinemaFilmInfoVO describeFilmInfoByFieldId(String fieldId);

    /**
     * 根据场次编号获取放映厅信息
     */
    FieldHallInfoVO describeHallInfoByFieldId(String fieldId);

}
