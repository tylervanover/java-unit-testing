package dev.vanovertech.pluralsight.junit5.intake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ScheduledApptTimeUtils {
    public static LocalDateTime convertToDateTimeFromString(String dateTime, LocalDate curdate)
            throws ScheduledApptException {
        String timeOnly = dateTime.replaceAll("(?i)today", "").trim();
        LocalDateTime localDateTime;

        try {
            if (dateTime.toUpperCase().contains("TODAY")) {
                localDateTime = LocalDateTime.of(curdate, LocalTime.parse(timeOnly.toUpperCase()
                        , DateTimeFormatter.ofPattern("h:mm a")));
            }
            else {
                localDateTime = LocalDateTime.parse(dateTime.toUpperCase()
                        , DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
            }
        } catch (Exception e) {
            throw new ScheduledApptException("Unable to create appointment from: [" + dateTime + "]");
        }
        return localDateTime;
    }
}
