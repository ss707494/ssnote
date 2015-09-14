package com.example.administrator.ssnote.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/9/13.
 */
public class DateUtil {
    public static SimpleDateFormat day_formate = new SimpleDateFormat("yyyy/MM/dd");

    public static SimpleDateFormat six_dataformate = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");

    public static String formatToDay(Long time) {
        return day_formate.format(new Date(time));
    }

    public static String[] sixformatToArray(long time) {
        return six_dataformate.format(new Date(time)).split(",");
    }
}
