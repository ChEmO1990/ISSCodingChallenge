package com.anselmo.isscodingchallenge.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anselmo on 12/3/2017.
 */

public class TimeStampUtil {

    /**
     * Return a formatted date
     *
     * @param timestamp - Current timestamp
     * @return CharSequence that represent a date
     */
    public static CharSequence getDateFromTimeStamp(long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp * 1000L);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(d);
    }
}
