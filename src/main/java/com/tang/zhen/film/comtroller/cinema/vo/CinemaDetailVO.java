package com.tang.zhen.film.comtroller.cinema.vo;

import com.tang.zhen.film.comtroller.common.BaseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import lombok.Builder;
import lombok.Data;

/**
 * 影院详情实体
 */
@Data
@Builder
public class CinemaDetailVO extends BaseVO {
    private String cinemaId;
    private String imgUrl;
    private String cinemaName;
    private String cinemaAdress;
    private String cinemaPhone;


    @Override
    public void checkParam() throws ParamErrorException {

    }
}
