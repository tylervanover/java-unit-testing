package dev.vanovertech.pluralsight.junit5.intake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static dev.vanovertech.pluralsight.junit5.intake.ScheduledApptTimeUtils.convertToDateTimeFromString;

public class ClinicCalendar {

    private List<PatientAppointment> appointments;

    private LocalDate curdate;

    public ClinicCalendar() {
        this.appointments = new ArrayList<>();
    }

    public ClinicCalendar(LocalDate curdate) {
        this();
        this.curdate = curdate;
    }

    public void addAppointment(String patientLastName, String patientFirstName, String doctorKey, String dateTime)
            throws ScheduledApptException {
        Doctor doctor = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime = convertToDateTimeFromString(dateTime, curdate);
        PatientAppointment appointment = new PatientAppointment(patientLastName, patientFirstName, localDateTime, doctor);
        appointments.add(appointment);
    }

    public List<PatientAppointment> getAppointments() {
        return Collections.unmodifiableList(this.appointments);
    }

    public List<PatientAppointment> getTodaysAppointments() {
        List<PatientAppointment> todaysAppointments = this.appointments
                    .stream()
                    .filter(a -> a.getAppointmentDateTime().toLocalDate().equals(curdate))
                    .collect(Collectors.toList());
        return (Collections.unmodifiableList(todaysAppointments));
    }

    public boolean hasAppointment(LocalDate date) {
        return appointments.stream().anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
    }
}
