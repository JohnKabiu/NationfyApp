package com.onetouch.tenant.emanyatta.data.local;

import java.util.Date;

import androidx.room.TypeConverter;

public class DateConverter {
    @TypeConverter
    public static Date toDate(long dateLong) {
        return new Date(dateLong);
    }

    @TypeConverter
    public static long fromDate(Date date) {
        return date.getTime();
    }
}

