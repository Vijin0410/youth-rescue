package com.rescue.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;

/**
 * 时间工具类
 * @author wanggang
 * @date 2024/12/30
 */
public class DateUtils {


    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final LocalTime startTime=LocalTime.of(0,0,0);
    public static final LocalTime endTime=LocalTime.of(23,59,59);


    /**
     * 获取指定日期的开始时间
     */
    public static String  getStartTimeStr(LocalDate localDate){
        return timeFormatter.format(LocalDateTime.of(localDate,startTime));
    }

    /**
     * 获取指定日期的结束时间
     */
    public static String  getEndTimeStr(LocalDate localDate){
        return timeFormatter.format(LocalDateTime.of(localDate,endTime));
    }

    /**
     * 获取月的最后一天
     */
    public static LocalDate lastDayOfMonth(LocalDate localDate){
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取周的开始时间
     */
    public static LocalDate getWeekStartDate(LocalDate localDate){
        return localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    /**
     * 获取指定月第一周开始时间
     */
    public static LocalDate getFirstWeekStartDate(LocalDate localDate){
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        // 获取当前月份第一天所在的周的第一天
        return firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    /**
     * 计算两个时间间隔多少秒
     */
    public static Long secondBetween(LocalDateTime d1,LocalDateTime d2){
        Duration duration = Duration.between(d1, d2);
        long seconds = duration.getSeconds();
        return Math.abs(seconds);
    }


    /**
     * 计算两个时间间隔多少分钟
     */
    public static Integer minutesBetween(LocalDateTime d1,LocalDateTime d2){
        Duration duration = Duration.between(d1, d2);
        return Math.abs(Math.toIntExact(duration.toMinutes()));
    }

    /**
     * 计算两个时间间隔多少小时
     */
    public static Integer hoursBetween(LocalDateTime d1,LocalDateTime d2){
        Duration duration = Duration.between(d1, d2);
        return Math.abs(Math.toIntExact(duration.toHours()));
    }

    /**
     * 获取两个日期间隔天数
     */
    public static Integer daysBetween(LocalDate date1,LocalDate date2){
        return Math.abs(Math.toIntExact(ChronoUnit.DAYS.between(date1, date2)));
    }

    /**
     * 获取两个日期间隔月份
     */
    public static Integer monthsBetween(LocalDate date1,LocalDate date2){
        date1=date1.withDayOfMonth(1);
        date2=date2.withDayOfMonth(1);
        int between = Math.abs((int)ChronoUnit.MONTHS.between(date1, date2));
        return between;
    }

    /**
     * 获取时间内所有周的开始和结束时间
     */
    public static List<LocalDate[]> getMonthWeekDateList(LocalDate localDate){
        List<LocalDate[]> weeks = new LinkedList<>();
        // 转换为YearMonth对象
        YearMonth yearMonth = YearMonth.from(localDate);
        // 获取该月的第一天
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        // 获取该月的最后一天
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        LocalDate startOfWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        while (!endOfWeek.isAfter(lastDayOfMonth)) {
            weeks.add(new LocalDate[]{startOfWeek, endOfWeek});
            startOfWeek = startOfWeek.plusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            endOfWeek = endOfWeek.plusWeeks(1).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        }
        return weeks;
    }

    /**
     * 秒格式化成 1:23:45 格式
     * @param seconds 秒数
     */
    public static String secondsFormat(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    /**
     * 判断时间是否在两个时间之间
     * @param currentDate 当前时间
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return true 在之间，false 不在之间
     */
    public static boolean isWithinRange(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        return (currentDate.isBefore(endDate) && currentDate.isAfter(startDate)) || currentDate.equals(startDate) || currentDate.equals(endDate);
    }
}
