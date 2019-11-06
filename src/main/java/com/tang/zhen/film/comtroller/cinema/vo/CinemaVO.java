package com.tang.zhen.film.comtroller.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 影院列表信息实体
 *
 */
@Data
public class CinemaVO implements Serializable {

    private String uuid;
    private String cinemaName;
    private String address;
    private String minimumPrice;
}
