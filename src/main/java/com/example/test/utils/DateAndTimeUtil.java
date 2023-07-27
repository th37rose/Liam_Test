package com.example.test.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static okhttp3.internal.Util.UTC;

/**
 * Utility class for date and time.
 *
 * @author mani
 */
@Slf4j
public class DateAndTimeUtil {

    public static final String  DATE_FORMAT = "dd-MM-yyyy";
    private static final String YYYY = "yyyy";
    private static final String YYYYMM = "yyyyMM";
    private static final String YYYYMMDD = "yyyyMMdd";

    public static long getCurrentEpochTime() {
        return (System.currentTimeMillis() / 1000);
    }

    public static long getYesterdayEpochTime() {

        long currentEpochTime = DateAndTimeUtil.getCurrentEpochTime();
        return (currentEpochTime - 86400);
    }

    public static String getDateAsString(long timestamp) {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date(timestamp));
    }

    /**
     * Convert from keepa Date to normal Date
     * @param keepaDate
     *  The item’s release date in one of the following three formats:<br>
     * 	YYYY or YYYYMM or YYYYMMDD (Y= year, M = month, D = day)<br>
     *  -1 if not available.<br><br>
     * 	Examples:<br>
     * 	1978 = the year 1978<br>
     * 	200301 = January 2003<br>
     * 	20150409 = April 9th, 2015
     *
     * @return date
     * @throws ParseException
     */

    public static Date fromKeepaDate(String keepaDate) {
        try {
            switch (keepaDate.length()) {
                case 4:
                    return new SimpleDateFormat(YYYY).parse(keepaDate);
                case 6:
                    return new SimpleDateFormat(YYYYMM).parse(keepaDate);
                case 8:
                    return new SimpleDateFormat(YYYYMMDD).parse(keepaDate);

                default:
                    return null;
            }
        } catch (ParseException | NumberFormatException ex) {
            log.trace("Ignored Parse Exception");
            return null;
        }
    }

    public static DateAndTimeRange getCurrentMonthDateAndTimeRangeWithRespectToReferenceDateAndTime(
            Long referenceDateAndTime) {

        DateAndTimeRange dateAndTimeRange = new DateAndTimeRange();

        LocalDate now = LocalDate.now();
        LocalDate referenceDate = Instant.ofEpochMilli(referenceDateAndTime)
                .atZone(ZoneId.systemDefault()).toLocalDate();
        referenceDate = referenceDate.withYear(now.getYear());
        referenceDate = referenceDate.withMonth(now.getMonthValue());
        if (now.getDayOfMonth() < referenceDate.getDayOfMonth()) {
            referenceDate = referenceDate.minusMonths(1l);
        } else {
            referenceDate = referenceDate.withMonth(now.getMonthValue());
        }
        dateAndTimeRange
                .setFrom(referenceDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        referenceDate = referenceDate.plusMonths(1l);
        dateAndTimeRange
                .setTo(referenceDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        return dateAndTimeRange;
    }

    public static Period getPeriodDiff(long time1, long time2) {

        LocalDate localDate1 = Instant.ofEpochMilli(time1).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = Instant.ofEpochMilli(time2).atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(localDate1, localDate2);
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
    }
    /**
     * get milliseconds only for date - yyyy-MM-DD
     * strDate: yyyy-MM-dd
     */
    public static Optional<Long> getMilliSec(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(UTC);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        long millis = date.getTime();
        return Optional.of(millis);
    }

    /**
     * get Today's date
     */
    public static String getTodayDate() {
        return new java.sql.Date((new Date()).getTime()).toString();
    }

    public static String getDateFromMillisecond(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(UTC);
        return sdf.format(new Date(time));
    }
}

