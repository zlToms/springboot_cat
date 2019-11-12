package com.tang.zhen.film.comtroller.film.vo.request;

import com.tang.zhen.film.comtroller.common.BaseVO;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import lombok.Data;

import java.io.Serializable;

@Data
public class DescribeFilmListReqVO extends BaseVO implements Serializable {
    private String showType = "1";    //判断获取电影的类型
    private String sortId = "99";
    private String catId = "99";
    private String yearId = "99";
    private String nowPage = "1";
    private String pageSize = "18";


    @Override
    public void checkParam() throws ParamErrorException {

    }
}
