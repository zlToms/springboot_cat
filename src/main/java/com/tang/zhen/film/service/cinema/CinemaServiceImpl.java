package com.tang.zhen.film.service.cinema;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.zhen.film.comtroller.cinema.vo.*;
import com.tang.zhen.film.comtroller.cinema.vo.condition.AreaResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.BrandResVO;
import com.tang.zhen.film.comtroller.cinema.vo.condition.HallTypeResVO;
import com.tang.zhen.film.comtroller.cinema.vo.request.DescribeCinemaRequestVO;
import com.tang.zhen.film.dao.entity.FilmAreaDictT;
import com.tang.zhen.film.dao.entity.FilmBrandDictT;
import com.tang.zhen.film.dao.entity.FilmCinemaT;
import com.tang.zhen.film.dao.entity.FilmHallDictT;
import com.tang.zhen.film.dao.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    private FilmFieldTMapper filmFieldTMapper;
    @Autowired
    private FilmCinemaTMapper cinemaTMapper;
    @Autowired
    private FilmHallFilmInfoTMapper filmInfoTMapper;
    @Autowired
    private FilmAreaDictTMapper areaDictTMapper;
    @Autowired
    private FilmHallDictTMapper hallDictTMapper;
    @Autowired
    private FilmBrandDictTMapper brandDictTMapper;

    @Override
    public Page<CinemaVO> describeCinemaInfo(DescribeCinemaRequestVO vo) {

        //组织Page对象 准备分页查询
        Page<FilmCinemaT> page = new Page<>(vo.getNowPage(),vo.getPageSize());
        //组织查询条件
        QueryWrapper<FilmCinemaT> wrapper = vo.genWrapper();
        //获取数据库返回值
        IPage<FilmCinemaT> filmCinemaTIPage = cinemaTMapper.selectPage(page, wrapper);

        //组织返回值
        Page<CinemaVO> cenimaPage = new Page<>(vo.getNowPage(),vo.getPageSize());
        cenimaPage.setTotal(page.getTotal());

        //这种东西一般人看不懂啊  将数据实体转换为表现层展示对象
        List<CinemaVO> cinemaVOS = filmCinemaTIPage.getRecords().stream().map((data)->{
            //数据转换
            CinemaVO cinemaVO = new CinemaVO();
            cinemaVO.setUuid(data.getUuid()+"");
            cinemaVO.setAddress(data.getCinemaAddress());
            cinemaVO.setCinemaName(data.getCinemaName());
            cinemaVO.setMinimumPrice(data.getMinimumPrice()+"");
            return cinemaVO;
        }).collect(Collectors.toList());
        cenimaPage.setRecords(cinemaVOS);
        return cenimaPage;
    }

    public boolean checkCondition(int conditionId,String conditionType){
        //验证brandId是否有效
        //如果无效，则应该经brandId = 99,并且将brandId = 99的isActive 设置为true
        switch (conditionType){
            case "brand":
                FilmBrandDictT filmBrandDictT = brandDictTMapper.selectById(conditionId);
                if(filmBrandDictT!=null&&filmBrandDictT.getUuid()!=null){
                    return true;
                }else{
                    return false;
                }
            case "area":
                FilmAreaDictT filmAreaDictT = areaDictTMapper.selectById(conditionId);
                if(filmAreaDictT!=null&&filmAreaDictT.getUuid()!=null){
                    return true;
                }else{
                    return false;
                }
            case "hallType":
                FilmHallDictT filmHallDictT = hallDictTMapper.selectById(conditionId);
                if(filmHallDictT != null && filmHallDictT.getUuid() != null){
                    return true;
                }else{
                    return false;
                }

        }
        return false;
    }
    @Override
    public List<BrandResVO> describeBrandConditions(final int brandId) {
        //获取所有列表
        List<FilmBrandDictT> brands= brandDictTMapper.selectList(null);

        //将对应的品牌设置为true
        List<BrandResVO> result =
                brands.stream().map((data)->{
                    BrandResVO brandResVO = new BrandResVO();
                    if(brandId == data.getUuid()){
                        brandResVO.setActive(true);
                    }
                    brandResVO.setBrandId(brandId+"");
                    brandResVO.setBrandName(data.getShowName());
                    return  brandResVO;
                }).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<AreaResVO> describeAreaConditions(final int areaId) {
        //获取所有列表
        List<FilmAreaDictT> areaDictTS = areaDictTMapper.selectList(null);

        List<AreaResVO> result =
                areaDictTS.stream().map((data)->{
                    AreaResVO areaResVO = new AreaResVO();
                    if(areaId == data.getUuid()){
                        areaResVO.setActive(true);
                    }
                    areaResVO.setAreaName(data.getShowName());
                    areaResVO.setAreaId(data.getUuid()+"");
                    return areaResVO;
                }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<HallTypeResVO> describeHallTypeConditions(final int hallTypeId) {
        List<FilmHallDictT> hallDictTS = hallDictTMapper.selectList(null);

        List<HallTypeResVO> result = hallDictTS.stream().map((data)->{
            HallTypeResVO hallTypeResVO = new HallTypeResVO();
            if(hallTypeId == data.getUuid()){
                hallTypeResVO.setActive(true);
            }
            hallTypeResVO.setHallTypeId(data.getUuid()+"");
            hallTypeResVO.setHallTypeName(data.getShowName());
            return hallTypeResVO;
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public CinemaDetailVO describeCinemaDetails(String cinemaId) {
        return null;
    }

    @Override
    public List<CinemaFilmVO> describeFieldsAndFilmInfo(String cinemaId) {
        return null;
    }

    @Override
    public CinemaFilmInfoVO describeFilmInfoByFieldId(String fieldId) {
        return null;
    }

    @Override
    public FieldHallInfoVO describeHallInfoByFieldId(String fieldId) {
        return null;
    }
}
