package com.tang.zhen.film.service.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tang.zhen.film.common.utils.ToolUtils;
import com.tang.zhen.film.comtroller.cinema.vo.FieldHallInfoVO;
import com.tang.zhen.film.comtroller.order.vo.response.OrderDetailResVO;
import com.tang.zhen.film.config.properties.OrderProperties;
import com.tang.zhen.film.dao.mapper.FilmOrderTMapper;
import com.tang.zhen.film.service.cinema.CinemaServiceAPI;
import com.tang.zhen.film.service.common.CommonServiceException;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImp implements OrderServiceAPI {

    @Autowired
    private FilmOrderTMapper filmOrderTMapper;

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @Autowired
    private OrderProperties orderProperties;


    /*
        检查座位信息
        -文件信息会在分布式的文件存储
        FILE_PATH + seat_address = E:/视频/享学vip/猫眼商城/课程资料（spring boot猫眼商城）/14-15 订单模块/resources/cgs.json
     */
    @Override
    public void checkSeats(String fieldId, String seats) throws CommonServiceException, IOException {
        FieldHallInfoVO fieldHallInfoVO = cinemaServiceAPI.describeHallInfoByFieldId(fieldId);
        if(fieldHallInfoVO == null || ToolUtils.isEmpty(fieldHallInfoVO.getSeatFile())){
            throw new CommonServiceException(404,"场次编号不正确");
        }

        String seatsPath = orderProperties.getFilePathPre() +fieldHallInfoVO.getSeatFile();

        //关闭文件流
        @Cleanup
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(seatsPath)
        );

        StringBuffer stringBuffer = new StringBuffer();
        String temp = new String();
        while((temp = bufferedReader.readLine()) != null){
            stringBuffer.append(temp);
        }
        JSONObject jsonObject = JSON.parseObject(stringBuffer.toString());
        //获取 ids节点
        String idsStr = jsonObject.getString("ids");
        List<String> idsList = Arrays.asList(idsStr.split(","));
        String[] seatsArray = seats.split(",");
        for(String seatId : seatsArray){
            boolean contains = idsList.contains(seatId);
            if(!contains){
                throw new CommonServiceException(500,"传入的信息有误");
            }
        }

    }

    @Override
    public void checkSoldSeats(String fieldId, String seats) throws CommonServiceException {

    }

    @Override
    public OrderDetailResVO saveOrder(String seatIds, String seatsNames, String fieldId, String userId) throws CommonServiceException {
        return null;
    }

    @Override
    public IPage<OrderDetailResVO> describeOrderInfoByUser(String userId) throws CommonServiceException {
        return null;
    }
}
