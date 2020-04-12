package dev.vanovertech.pluralsight.junit5.intake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static dev.vanovertech.pluralsight.junit5.intake.ScheduledApptTimeUtils.convertToDateTimeFromString;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduledApptTimeUtilsTest {

    private LocalDate today = LocalDate.now();

    @Test
    @DisplayName("Assert that invalid appointment date throws ScheduledApptException")
    void test_addAppointment_returnsScheduledApptException_whenInvalidFormat() {
        assertThrows(ScheduledApptException.class, () -> convertToDateTimeFromString("APR 04 2019 13:53", today));
    }

}
