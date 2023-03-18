package com.kkwo.AM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
public class Util {
    public static String getNowDateTimeStr() {
 
        // 현재 날짜/시간
        LocalDateTime now = LocalDateTime.now(); // 2021-06-17T06:43:21.419878100
 
        // 포맷팅
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 2023-03-11 12:12:12

        return formatedNow;
    }
}