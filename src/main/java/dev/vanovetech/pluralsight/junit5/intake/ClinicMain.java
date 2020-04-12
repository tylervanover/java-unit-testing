package dev.vanovetech.pluralsight.junit5.intake;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClinicMain {

    private static ClinicCalendar calendar;

    public static void main(String[] args) {
        calendar = new ClinicCalendar();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Patient Intake System\n\n");
        String lastOption = "";
        while (!lastOption.equalsIgnoreCase("X")) {
            lastOption = displayMenu(scanner);
        }
    }

    private static String displayMenu(Scanner scanner) {
        System.out.println("Select an option:");
        System.out.println("1. Add a new appointment");
        System.out.println("2. View all appointments");
        System.out.println("X. Exit System");
        System.out.println("Option: ");
        String option = scanner.next().toUpperCase();
        switch (option) {
            case "1":
                performPatientEntry(scanner);
                return option;
            case "2":
                listAllAppointments(scanner);
                return option;
            case "X":
                return option;
            default:
                System.out.println("Invalid option, try again");
                return option;
        }
    }

    private static void performPatientEntry(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n\nEnter appointment information:");
        System.out.print(" Patient Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print(" Patient First Name: ");
        String firstName = scanner.nextLine();

        System.out.print(" Scheduled Date (M/d/yyyy h:m a): ");
        String scheduledDateTime = scanner.nextLine();

        System.out.print(" Doctor Last Name: ");
        String physicianLastName = scanner.nextLine();

        try {
            calendar.addAppointment(lastName, firstName, physicianLastName, scheduledDateTime);
        } catch (ScheduledApptException e) {
            System.out.println("Error when scheduling appontment: " + e.getMessage());
            return;
        }
        System.out.println("Appointment entered successfully.\n\n");
    }

    private static void listAllAppointments(Scanner scanner) {
        System.out.println("\n\nAll appointments in system:");
        for (PatientAppointment appointment : calendar.getAppointments()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String apptTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.println(String.format("%s: %s, %s\t\tDoctor: %s", apptTime, appointment.getPatientLastName()
                    , appointment.getPatientFirstName(), appointment.getDoctor().getName()));
        }
        System.out.println("\nPress any key to continue...");
        scanner.nextLine();
    }
}
