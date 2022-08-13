package com.jd.edi.demo;

import ch.qos.logback.core.util.TimeUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class code {
    public static final String TIME_ZONE = "Asia/Shanghai";

    public static void main(String[] args) {

        String sTimeStr = "2022-04-01 00:00:00";
        String eTimeStr = "2022-04-30 23:59:59";

        Date startTime = null;
        Date endTime = null;
        String tableName = "t_coding_commit_kpi";
        if(!StringUtils.isEmpty(sTimeStr)){
            startTime = parse(sTimeStr,"yyyy-MM-dd HH:mm:ss");
            System.out.println(startTime.toString());
            System.out.println(format(startTime, "yyyyMMdd"));
            tableName = tableName+"_"+ format(startTime, "yyyyMM");
        }else{
            tableName = tableName+"_"+ format(getCurrTimestamp(), "yyyyMM");
        }
        System.out.println("++++++++++collection table: "+tableName);
    }

    public static String format(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        sf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return sf.format(date);
    }


    public static Date parse(String str, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            sf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
            return sf.parse(str);
        } catch (ParseException e) {

        }
        return null;
    }

    public static Timestamp getCurrTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
