package com.yxy.cl.Utils;


import java.sql.Timestamp;
import java.util.Date;

public class TimeUtils {
    public static Timestamp nowTime() {
        return new Timestamp(new Date().getTime());
    }
}
