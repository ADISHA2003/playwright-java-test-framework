package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelperService {

    public static String getCurrentDateTimeInFormat(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getExecutionTimeInMilliSeconds(long startTime, long endTime) {
        return String.valueOf(endTime - startTime);
    }

    public static long getCurrentTimeInMilliSeconds() {
        return System.currentTimeMillis();
    }
}
