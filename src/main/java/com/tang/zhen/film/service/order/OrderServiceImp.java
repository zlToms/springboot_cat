package com.tang.zhen.film.service.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.zhen.film.common.utils.ToolUtils;
import com.tang.zhen.film.comtroller.cinema.vo.CinemaFilmInfoVO;
import com.tang.zhen.film.comtroller.cinema.vo.FieldHallInfoVO;
import com.tang.zhen.film.comtroller.order.vo.response.OrderDetailResVO;
import com.tang.zhen.film.config.properties.OrderProperties;
import com.tang.zhen.film.dao.entity.FilmOrderT;
import com.tang.zhen.film.dao.mapper.FilmOrderTMapper;
import com.tang.zhen.film.service.cinema.CinemaServiceAPI;
import com.tang.zhen.film.service.common.CommonServiceException;
import com.tang.zhen.film.service.order.bo.OrderPriceBO;
import io.swagger.models.auth.In;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    /*
       检查待售的座位是否包含已经售卖的座位，有则抛异常
     */
    @Override
    public void checkSoldSeats(String fieldId, String seats) throws CommonServiceException {
        String soldSeats = filmOrderTMapper.describeSoldSeats(fieldId);

        List<String> idsList = Arrays.asList(soldSeats.split(","));
        String[] seatsArray = seats.split(",");
        for(String seatId : seatsArray){
            boolean contains = idsList.contains(seatId);
            if(contains){
                throw new CommonServiceException(500,seatId+"为已售座位,不能重复销售");
            }
        }
    }
    /*
        根据用户编号，获取用户订单信息列表
     */
    @Override
    public IPage<OrderDetailResVO> describeOrderInfoByUser(int nowPage, int pageSize ,String userId) throws CommonServiceException {
        Page<FilmOrderT> page = new Page<>(nowPage,pageSize);
        return filmOrderTMapper.describeOrderDtailsByUser(page,userId);
    }

    @Override
    public OrderDetailResVO saveOrder(String seatIds, String seatsNames, String fieldId, String userId) throws CommonServiceException {

        //非空检验 seatIds

        String uuid = UUID.randomUUID().toString().replace("-","");

        OrderPriceBO orderPriceBO = filmOrderTMapper.describeOrderPriceByFieldId(fieldId);

        //单个座位的票价
        double filmPrice = orderPriceBO.getFilmPrice();
        //座位数
        int seatNum = seatIds.split(",").length;
        //总票价
        double totalPrice = getTotalPrice(filmPrice,seatNum);

        //获取filmId
        CinemaFilmInfoVO cinemaFilmInfoVO = cinemaServiceAPI.describeFilmInfoByFieldId(fieldId);

        FilmOrderT filmOrderT = new FilmOrderT();
        filmOrderT.setUuid(uuid);
        filmOrderT.setSeatsName(seatsNames);
        filmOrderT.setSeatsIds(seatIds);
        filmOrderT.setOrderUser(Integer.parseInt(userId));
        filmOrderT.setOrderPrice(totalPrice);
        filmOrderT.setFilmPrice(filmPrice);
        filmOrderT.setFilmId(Integer.parseInt(cinemaFilmInfoVO.getFilmId()));
        filmOrderT.setFieldId(Integer.parseInt(fieldId));
        filmOrderT.setCinemaId(Integer.parseInt(orderPriceBO.getCinemaId()));

        filmOrderTMapper.insert(filmOrderT);

        return filmOrderTMapper.describeOrderDetailsById(uuid);
    }

    //计算总票价
    private  double getTotalPrice (double filmPrice, int seatNum){
        BigDecimal b1 = new BigDecimal(filmPrice);
        BigDecimal b2 = new BigDecimal(seatNum);

        BigDecimal multiply = b1.multiply(b2);

        //小数点后取两位，同时四舍五入
        BigDecimal result = multiply.setScale(2, RoundingMode.UP);

        return result.doubleValue();
    }


}
