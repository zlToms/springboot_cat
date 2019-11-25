package com.tang.zhen.film.comtroller.cinema.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldHallInfoVO implements Serializable {

    private String hallFieldId;
    private String hallName;
    private String price;
    private String seatFile;
    private String soldSeats;

}
