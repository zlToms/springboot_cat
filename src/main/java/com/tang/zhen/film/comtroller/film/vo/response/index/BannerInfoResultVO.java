package com.tang.zhen.film.comtroller.film.vo.response.index;

import lombok.Data;

import java.io.Serializable;

@Data
public class BannerInfoResultVO implements Serializable {
    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;
}
