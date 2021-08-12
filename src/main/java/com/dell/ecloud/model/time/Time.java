package com.dell.ecloud.model.time;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Time implements ITime {

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    private String stringTime;

    public Time() {
        update();
    }

    @Override
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
