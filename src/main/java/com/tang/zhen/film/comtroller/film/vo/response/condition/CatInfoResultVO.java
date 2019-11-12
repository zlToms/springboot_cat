package com.tang.zhen.film.comtroller.film.vo.response.condition;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatInfoResultVO implements Serializable {

    private String cartId;
    private String cartName;
    private String isActive;
}
