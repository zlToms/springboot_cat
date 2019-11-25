package com.tang.zhen.film.comtroller.order.vo.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailResVO implements Serializable {

    private String orderId;
    private String filmName;
    private String cinemaName;
    private String fieldTime;
    private String seatsName;
    private String orderPrice;
    private String orderStatus;


}
