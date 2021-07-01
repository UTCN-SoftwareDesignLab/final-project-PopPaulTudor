package com.lab4.demo.utills;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CalendarUtills {

    public static String getStringOfMillis(long millis){

        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        System.out.println(ldt);

        return formmat1.format(ldt);
    }
}
