package com.finance.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : shenhao
 * @date : 2019/11/15 9:01
 */
public class LocalDateTimeUtils {

    public static final String PURE_DATETIME = "yyyyMMddHHmmss";
    public static final DateTimeFormatter pureDateTime= DateTimeFormatter.ofPattern(PURE_DATETIME);
    public static String getPureDateTime(LocalDateTime localDateTime){
        return pureDateTime.format(localDateTime);
    }
    public static LocalDateTime parsePureDateTime(String localDateTime){
        return LocalDateTime.parse(localDateTime,pureDateTime);
    }

}
