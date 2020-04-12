package dev.vanovertech.pluralsight.junit5.intake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

class ClinicMainTest {

    private ClinicCalendar calendar;

    private static final String PATIENT_LAST_NAME = "Johnson";
    private static final String PATIENT_FIRST_NAME = "Tyler";
    private static final String PHYSICIAN_LAST_NAME = "Avery";
    private static final String APPOINTMENT_DATE_TIME = "09/01/2018 2:00 pm";

    @BeforeEach
    void setUp() {
        calendar = new ClinicCalendar();
    }

    @Test
    @DisplayName("Allow entry of a new appointment")
    void test_allowEntryOfAppointment() throws ScheduledApptException {
        calendar.addAppointment(
                PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, APPOINTMENT_DATE_TIME
        );
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertThat(appointments, is(notNullValue()));
        assertThat(appointments, hasSize(1));
    }

}