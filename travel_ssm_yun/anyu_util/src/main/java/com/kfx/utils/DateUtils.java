package com.kfx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //字符串转日期
    public static String dateToString(Date date, String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(date);
        return format;
    }

    //日期转字符串
    public  static Date stringToDate(String string,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        try {
          return  simpleDateFormat.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换异常");
        }

    }
}
