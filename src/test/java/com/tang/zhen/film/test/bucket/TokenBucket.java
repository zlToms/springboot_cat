package com.tang.zhen.film.test.bucket;

public class TokenBucket {

    private int bucketNum = 10;                //令牌桶容量
    private int rate = 1 ;                      //令牌流入速率
    private  int nowTokens;                     //当前令牌数量
    private  long timestamp =getNowTime();      //时间


    /*
        判断当前是否有令牌可用
     */
    public boolean hasToken(){
        //记录当前拿取令牌的时间
        long nowTime = getNowTime();
        //添加令牌
        nowTokens = nowTokens + (int)((nowTime -timestamp) * rate);
        // nowTokens 有可能超出令牌桶上限
        nowTokens = describeTokens(nowTokens);
        System.out.println("当前令牌数量："+nowTokens);
        //更新最后拿取令牌的时间
        timestamp = nowTime;

        if(nowTokens >= 1){
            nowTokens -= 1;
            return true;
        }

        return false;
    }


    //判断令牌是否超出桶的上限
    private int describeTokens(int tokenNum){
        if(bucketNum > tokenNum){
            return tokenNum;
        }else {
            return bucketNum;
        }
    }


    private long getNowTime(){
        return System.currentTimeMillis();
    }

}
