package com.tang.zhen.film.service.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tang.zhen.film.comtroller.order.vo.response.OrderDetailResVO;
import com.tang.zhen.film.service.common.CommonServiceException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OrderServiceAPI {

    /*
       检查座位是否符合现状
     */
    void checkSeats(String fieldId,String seats) throws CommonServiceException, IOException;

    /*
       检查座位是否为已售座位
     */
    void checkSoldSeats(String fieldId,String seats)throws CommonServiceException;

    /*
    保存订单信息
     */
    OrderDetailResVO saveOrder(String seatIds,String seatsNames,String fieldId,String userId)throws CommonServiceException;

    /*
    根据用户编号，获取用户购买过的电影订单信息
     */
    IPage<OrderDetailResVO> describeOrderInfoByUser(String userId)throws CommonServiceException;
}
