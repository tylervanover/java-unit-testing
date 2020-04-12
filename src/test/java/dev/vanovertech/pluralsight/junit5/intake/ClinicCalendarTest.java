package dev.vanovertech.pluralsight.junit5.intake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

class ClinicCalendarTest {

    private ClinicCalendar calendar;
    private LocalDate today = LocalDate.now();
    private LocalDate tomorrow = LocalDate.now().plusDays(1);

    private static final String PATIENT_LAST_NAME = "Johnson";
    private static final String PATIENT_FIRST_NAME = "Tyler";
    private static final String PHYSICIAN_LAST_NAME = "Avery";
    private static final String APPOINTMENT_DATE_TIME = "9/1/2018 2:00 pm";

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
        assertThat(appointments.get(0).getPatientFirstName(), is(PATIENT_FIRST_NAME));
        assertThat(appointments.get(0).getPatientLastName(), is(PATIENT_LAST_NAME));
        assertThat(appointments.get(0).getDoctor(), is(Doctor.avery));
        assertThat(appointments.get(0).getAppointmentDateTime()
                .format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")).toLowerCase()
                , is(APPOINTMENT_DATE_TIME));
    }

    @Test
    @DisplayName("Has Appointments returns true if there are appointments")
    void test_hasAppointments_returnsTrue_whenThereAreAppointments() throws ScheduledApptException {
        calendar.addAppointment(
                PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, APPOINTMENT_DATE_TIME
        );
        assertThat(calendar.hasAppointment(LocalDate.of(2018, 9, 1)), is(true));
    }

    @Test
    @DisplayName("Has Appointments returns false if there are no appointments")
    void test_hasAppointments_returnsFalse_whenThereAreNoAppointments() throws ScheduledApptException {
        calendar.addAppointment(
                PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, APPOINTMENT_DATE_TIME
        );
        assertThat(calendar.hasAppointment(LocalDate.of(1990, 1, 1)), is(false));
    }

    @Test
    @DisplayName("Has todays appointments")
    void test_todaysAppointments_hasCorrectList() throws ScheduledApptException {
        calendar = new ClinicCalendar(today);
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "today 2:00 pm");
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "today 3:00 pm");
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "today 4:00 pm");
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME
                , tomorrow.format(DateTimeFormatter.ofPattern("M/d/yyyy")) + " 4:00 pm");
        assertThat(calendar.getTodaysAppointments(), hasSize(3));
        assertThat(calendar.getAppointments(), hasSize(4));
    }

    @Test
    @DisplayName("Using 'today' keyword is case invariant")
    void test_todaysAppointments_caseInvariant() throws ScheduledApptException {
        calendar = new ClinicCalendar(today);
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "TODAY 2:00 pm");
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "today 3:00 pm");
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "ToDaY 4:00 pm");
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "tOdAy 5:00 pm");
        calendar.addAppointment(PATIENT_LAST_NAME, PATIENT_FIRST_NAME, PHYSICIAN_LAST_NAME, "Today 6:00 pm");
        assertThat(calendar.getTodaysAppointments(), hasSize(5));
    }
}