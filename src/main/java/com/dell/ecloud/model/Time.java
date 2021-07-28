package com.dell.ecloud.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Time {

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    private String stringTime;

    public Time() {
        update();
    }

    public Time update() {
        LocalDateTime localDateTime = LocalDateTime.now();
        stringTime = localDateTime.format(formatter);
        return this;
    }

    @Override
    public String toString() {
        return stringTime;
    }

}
