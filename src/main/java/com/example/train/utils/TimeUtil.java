package com.example.train.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    private static final String format = "EEE MMM dd HH:mm:ss zzz yyyy";

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new
             ThreadLocal<SimpleDateFormat>();

//    private static SimpleDateFormat sdf =
//            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

    public static Date stringToDate(String sTime){
        SimpleDateFormat sdf = null;
        sdf = threadLocal.get();
        if (sdf == null)
            sdf = new SimpleDateFormat(format,Locale.US);
        Date time = null;

        try{
            time = sdf.parse(sTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static boolean isTimeout(String time){

        SimpleDateFormat sdf = null;
        sdf = threadLocal.get();
        if (sdf == null)
            sdf = new SimpleDateFormat(format,Locale.US);

        Date loginTime = null;
        try {
            loginTime = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date nowTime = new Date(System.currentTimeMillis());
        return (nowTime.getTime() - loginTime.getTime())/1000/60/60 < 1;
    }
}
