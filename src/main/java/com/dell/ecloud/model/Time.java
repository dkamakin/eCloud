package com.dell.ecloud.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @Getter
    private static String time;

    public Time() {
        update();
    }

    public Time update() {
        LocalDateTime localDateTime = LocalDateTime.now();
        time = localDateTime.format(formatter);
        return this;
    }

}
