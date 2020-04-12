package dev.vanovertech.pluralsight.junit5.intake;

import java.time.LocalDateTime;

public class PatientAppointment {
    private final String patientLastName;
    private final String patientFirstName;
    private final LocalDateTime appointmentDateTime;
    private final Doctor doctor;

    public PatientAppointment(String patientLastName, String patientFirstName
            , LocalDateTime appointmentDateTime, Doctor doctor) {
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
