package dev.vanovetech.pluralsight.junit5.intake;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ClinicCalendar {

    private List<PatientAppointment> appointments;

    public ClinicCalendar() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(String patientLastName, String patientFirstName, String doctorKey, String dateTime)
            throws ScheduledApptException {
        Doctor doctor = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.parse(dateTime.toUpperCase()
                    , DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
        } catch (Exception e) {
            throw new ScheduledApptException("Unable to create appointment from: [" + dateTime.toUpperCase() + "]");
        }
        PatientAppointment appointment = new PatientAppointment(patientLastName, patientFirstName, localDateTime, doctor);
        appointments.add(appointment);
    }

    public List<PatientAppointment> getAppointments() {
        return Collections.unmodifiableList(this.appointments);
    }
}
