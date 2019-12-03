package com.tang.zhen.film.comtroller.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.util.concurrent.RateLimiter;
import com.tang.zhen.film.comtroller.common.BaseResponseVO;
import com.tang.zhen.film.comtroller.common.TraceUtil;
import com.tang.zhen.film.comtroller.exception.ParamErrorException;
import com.tang.zhen.film.comtroller.order.vo.response.OrderDetailResVO;
import com.tang.zhen.film.service.common.CommonServiceException;
import com.tang.zhen.film.service.order.OrderServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceAPI orderServiceAPI;

    @RequestMapping(value = "/buyTicket",method = RequestMethod.GET)
    public BaseResponseVO buyTicket(String fieldId,String soldSeats,String seatsName) throws CommonServiceException {
        //购票的限流措施
        RateLimiter rateLimiter = RateLimiter.create(20);
        rateLimiter.acquire(1);

        //soldSeats 验证是否为真正有效的座位信息
        orderServiceAPI.checkSoldSeats(fieldId,soldSeats);
        //soldSeats 是否是未销售的座位
        orderServiceAPI.checkSoldSeats(fieldId,soldSeats);

        String userId = TraceUtil.getUserId();
        OrderDetailResVO orderDetailResVO = orderServiceAPI.saveOrder(soldSeats, seatsName, fieldId, userId);

        return BaseResponseVO.success(orderDetailResVO);
    }


    @RequestMapping(value = "/getOrderInfo",method = RequestMethod.GET)
    public BaseResponseVO getOrderInfo(
            @RequestParam(name = "nowPage",required = false ,defaultValue = "1") String nowPage,
            @RequestParam(name = "pageSize",required = false ,defaultValue = "5") String pageSize) throws ParamErrorException, CommonServiceException {
        checkGetOrderInfoParams( nowPage, pageSize);

        //jwt里面的
        String userId = TraceUtil.getUserId();
        IPage<OrderDetailResVO> orderDetailResVOIPage =
                orderServiceAPI.describeOrderInfoByUser(Integer.parseInt(nowPage), Integer.parseInt(pageSize), userId);

        return BaseResponseVO.success(
                orderDetailResVOIPage.getCurrent(),
                orderDetailResVOIPage.getPages(),
                orderDetailResVOIPage.getRecords());
    }


    private void checkGetOrderInfoParams(String nowPage,String pageSize)throws ParamErrorException {
        //验证nowPage和pageSize是否为数字


    }


}
