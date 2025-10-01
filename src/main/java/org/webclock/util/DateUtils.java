package org.webclock.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class DateUtils {

    private DateUtils() {
    }

    public static boolean isTodayLastBusinessDayOfMonth() {
        return isDateLastBusinessDayOfMonth(LocalDate.now());
    }

    public static boolean isDateLastBusinessDayOfMonth(LocalDate date) {
        YearMonth ym = YearMonth.from(date);
        LocalDate lastDay = ym.atEndOfMonth();

        // If last day is Saturday, move back to Friday
        if (lastDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            lastDay = lastDay.minusDays(1);
        }
        // If last day is Sunday, move back to Friday
        else if (lastDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            lastDay = lastDay.minusDays(2);
        }

        return date.equals(lastDay);
    }

}
