package com.magalu.notification.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateUtilTest {
    @Test
    void getDurationReturnsCorrectDuration() {
        LocalDateTime fromDateTime = LocalDateTime.of(2022, 1, 1, 0, 0);
        assertEquals("Há 1 ano", DateUtil.getDuration(fromDateTime, fromDateTime.plusYears(1)));
        assertEquals("Há 6 meses", DateUtil.getDuration(fromDateTime, fromDateTime.plusMonths(6)));
        assertEquals("Há 15 dias", DateUtil.getDuration(fromDateTime, fromDateTime.plusDays(15)));
        assertEquals("Há 1 hora", DateUtil.getDuration(fromDateTime, fromDateTime.plusHours(1)));
        assertEquals("Há 30 minutos", DateUtil.getDuration(fromDateTime, fromDateTime.plusMinutes(30)));
        assertEquals("Há 20 segundos", DateUtil.getDuration(fromDateTime, fromDateTime.plusSeconds(20)));
        
        assertEquals("Há 2 anos", DateUtil.getDuration(fromDateTime));
    }
}
