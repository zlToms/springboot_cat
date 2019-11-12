package com.tang.zhen.film.comtroller.film.vo.response.films;

import lombok.Data;

import java.io.Serializable;

@Data
public class DescribeFilmListResultVO implements Serializable {

    private  String filmId;
    private  String filmType;
    private  String filmName;
    private  String imgAddress;
    private  String filmScore;
}
