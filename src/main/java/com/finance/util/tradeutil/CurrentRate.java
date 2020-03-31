package com.finance.util.tradeutil;

import com.finance.util.HttpUtils;

/**
 * @author : shenhao
 * @date : 2020/3/27 13:47
 */
public class CurrentRate {

    public static double getCurrentPrice(String tradeCode){
        String uri = "http://hq.sinajs.cn/list=" + tradeCode;
        String s = HttpUtils.get(uri);
        String[] strings = s.split(",");
        return Double.valueOf(strings[1]);
    }

}
