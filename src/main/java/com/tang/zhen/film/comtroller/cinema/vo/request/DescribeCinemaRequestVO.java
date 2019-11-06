package com.tang.zhen.film.comtroller.cinema.vo.request;

import com.tang.zhen.film.comtroller.common.BaseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import lombok.Data;

@Data
public class DescribeCinemaRequestVO  extends BaseVO {

    private Integer brandId;
    private Integer  hallType;
    private Integer districtId;
    private Integer  pageSize;
    private Integer nowPage;

    @Override
    public void checkParam() throws ParamErrorException {

    }
}
