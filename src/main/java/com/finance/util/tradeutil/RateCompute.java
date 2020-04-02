package com.finance.util.tradeutil;

import com.finance.web.entity.po.TradeRecord;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author : shenhao
 * @date : 2020/3/24 16:29
 * 采用Excel的xiss方法计算年收益率
 */
public class RateCompute {

    private static double maxRate = 10d;
    private static double minRate = -10d;

    private static int maxNumber = 2000;
    private static double maxError = 0.0001;

    public static Double[] allData(List<TradeRecord> tradeRecords){
        if (!isAvailable(tradeRecords)){
            return new Double[]{0d,0d};
        }
        int length = tradeRecords.size();
        if (length < 2) return new Double[]{0d,0d};
        Double[] records = new Double[length];
        LocalDate[] dates = new LocalDate[length];
        Double income = 0d;
        for (int i = 0; i < length; i++) {
            TradeRecord tradeRecord = tradeRecords.get(i);
            if ("D00201".equals(tradeRecord.getType())){
                records[i] = tradeRecord.getTotal() * -1;
            }else{
                records[i] = tradeRecord.getTotal();
            }
            dates[i] = tradeRecord.getTradeDate();
            income += records[i];
        }
        Double[] result = new Double[2];
        result[0] = compute(records, dates);
        result[1] = income;
        return result;
    }

    public static Boolean isAvailable(List<TradeRecord> tradeRecords){
        int flag = 0;
        for (TradeRecord item : tradeRecords){
            if ("D00201".equals(item.getType())) {
                if (flag == 1) return true;
                else flag = -1;
            }else{
                if (flag == -1) return true;
                else flag = 1;
            }
        }
        return false;
    }

    public static Double compute(List<TradeRecord> tradeRecords){
        int length = tradeRecords.size();
        if (length < 2) return 0d;
        Double[] records = new Double[length];
        LocalDate[] dates = new LocalDate[length];
        for (int i = 0; i < length; i++) {
            TradeRecord tradeRecord = tradeRecords.get(i);
            if ("D00201".equals(tradeRecord.getType())){
                records[i] = tradeRecord.getTotal() * -1;
            }else{
                records[i] = tradeRecord.getTotal();
            }
            dates[i] = tradeRecord.getTradeDate();
        }
        return compute(records,dates);
    }

    public static Double compute(Double[] records, LocalDate[] dates){
        double max = maxRate;
        double min = minRate;
        double tempRate = 0d;
        for (int i = 0; i < maxNumber; i++) {
            tempRate = (max + min) / 2;
            double result = getError(records,dates,tempRate);
            if (Math.abs(result) < maxError) return tempRate;
//            System.out.println(result);
            if (result < 0) max = tempRate;
            else min = tempRate;
        }

        return 0d;
    }

    public static double getError(Double[] records, LocalDate[] dates, double rate){
        double count = records[0];
        for (int i = 1; i < records.length; i++) {
            count = count * (1+(rate * dates[i-1].until(dates[i] , ChronoUnit.DAYS) / 365)) + records[i];
        }
        return count;
    }
// http://hq.sinajs.cn/list=f_260108
//    1.674,3.311,1.63,2020-03-25,94.0171";
//    public static void main(String[] args) {
//        Double[] records = {-1000d,-2000d,-3000d,-3000d,-2000d,2660.64d,2774.28,2874.92,2534.42,148.7};
//        LocalDate[] dates = new LocalDate[]{
//                LocalDate.of(2018,7,7),
//                LocalDate.of(2018,7,13),
//                LocalDate.of(2018,8,21),
//                LocalDate.of(2018,9,19),
//                LocalDate.of(2018,10,26),
//                LocalDate.of(2019,3,9),
//                LocalDate.of(2019,4,15),
//                LocalDate.of(2019,4,27),
//                LocalDate.of(2019,6,16),
//                LocalDate.of(2020,3,24),
//        };
//        Double rate = compute(records, dates);
//        System.out.println("结果为：" + rate);
//
//    }

}
