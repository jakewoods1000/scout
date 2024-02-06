package com.app.cfd;
import java.sql.Timestamp;
import java.util.Calendar;

public class TimeUtils {

    public Timestamp now() {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }
}
