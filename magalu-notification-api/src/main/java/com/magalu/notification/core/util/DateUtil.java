package com.magalu.notification.core.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public final class DateUtil {
    private DateUtil() {
        // empty
    }

    public static String getDuration(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        StringBuilder toReturn = new StringBuilder("");
        Period period = Period.between(fromDateTime.toLocalDate(), toDateTime.toLocalDate());
        Duration duration = Duration.between(fromDateTime, toDateTime);

        final int MINUTES_PER_HOUR = 60;
        final int SECONDS_PER_MINUTE = 60;
        final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        seconds = (seconds % SECONDS_PER_MINUTE);

        toReturn.append("Há ");
        if (years > 0) {
            toReturn.append(years);
            toReturn.append(DateUtil.singularOrPlural(years, " ano", " anos"));
        } else if (months > 0) {
            toReturn.append(months);
            toReturn.append(DateUtil.singularOrPlural(months, " mês", " meses"));
        } else if (days > 0) {
            toReturn.append(days);
            toReturn.append(DateUtil.singularOrPlural(days, " dia", " dias"));
        } else if (hours > 0) {
            toReturn.append(hours);
            toReturn.append(DateUtil.singularOrPlural(hours, " hora", " horas"));
        } else if (minutes > 0) {
            toReturn.append(minutes);
            toReturn.append(DateUtil.singularOrPlural(minutes, " minuto", " minutos"));
        } else if (seconds > 0) {
            toReturn.append(seconds);
            toReturn.append(DateUtil.singularOrPlural(seconds, " segundo", " segundos"));
        }

        return toReturn.toString();
    }

    public static String getDuration(LocalDateTime fromDateTime) {
        return DateUtil.getDuration(fromDateTime, LocalDateTime.now());
    }

    private static String singularOrPlural(long value, String singular, String plural) {
        return (value <= 1) ?  singular : plural;
    }
}
