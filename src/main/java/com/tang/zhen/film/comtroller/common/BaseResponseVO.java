package com.tang.zhen.film.comtroller.common;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
public final class BaseResponseVO<V> {
    private BaseResponseVO(){}

    //返回抓状态【0-成功，1-业务失败，999-系统异常】
    private int status;
    //返回信息
    private String msg;
    //返回数据实体
    private V data;

    //图片前缀
    private String imgPre;

    //分页使用
    private Integer nowPage;
    private Integer totalPage;


    public static<V> BaseResponseVO success(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        return baseResponseVO;
    }


    public static<V> BaseResponseVO success(String msg){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }

    public static<V> BaseResponseVO success(V data){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    public static<V> BaseResponseVO success(V data,String msg){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setData(data);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }

    public static<V> BaseResponseVO seccess(int nowPage,int totalPage,String imgPre,V data){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setData(data);
        baseResponseVO.setImgPre(imgPre);
        baseResponseVO.setTotalPage(totalPage);
        baseResponseVO.setNowPage(nowPage);
        return baseResponseVO;
    }

    public static<V> BaseResponseVO seccess(String imgPre,V data){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setData(data);
        baseResponseVO.setImgPre(imgPre);

        return baseResponseVO;
    }

    public static<V> BaseResponseVO serviceFaied(String msg){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(1);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }

    public static<V> BaseResponseVO serviceFaied(int status,String msg){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(status);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }


    public static<V> BaseResponseVO serviceFaied(String msg,V data){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(1);
        baseResponseVO.setMsg(msg);
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    public static<V> BaseResponseVO systemError(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(999);
        baseResponseVO.setMsg("系统异常，请联系管理员！");
        return baseResponseVO;
    }

    public static<V> BaseResponseVO noLogin(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(700);
        baseResponseVO.setMsg("用户没有登陆！");
        return baseResponseVO;
    }
}
