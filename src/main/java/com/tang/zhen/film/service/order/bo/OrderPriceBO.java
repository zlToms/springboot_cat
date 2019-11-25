package com.tang.zhen.film.service.order.bo;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

@Data
public class OrderPriceBO implements Serializable {

    private String cinemaId;
    private Double filmPrice;
}
