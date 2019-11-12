package com.tang.zhen.film.comtroller.film.vo.response.index;

import lombok.Data;

import java.io.Serializable;

@Data
public class SoonFilmListResultVO implements Serializable {

    private String filmId;
    private String filmType;
    private String ingAdress;
    private String filmName;
    private String expectNum;
    private String showTime;
}
