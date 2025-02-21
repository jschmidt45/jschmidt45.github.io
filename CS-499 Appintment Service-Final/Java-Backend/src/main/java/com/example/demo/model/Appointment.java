package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Appointment {

    @Id
    @Column(nullable = false, unique = true, length = 10)
    private String appointmentID; // Primary key

    @Column(nullable = false)
    private LocalDate appointmentDate; // Appointment date

    @Column(nullable = false, length = 50)
    private String appointmentDescription; // Short description

    @Column(nullable = false, length = 50)
    private String username; // The user associated with this appointment

    @Version
    private Integer version;

    // Default constructor required by JPA
    public Appointment() {}

    // Parameterized constructor
    public Appointment(String appointmentID, LocalDate appointmentDate, String appointmentDescription, String username) {
        if (appointmentID == null || appointmentID.length() > 10) {
            throw new IllegalArgumentException("Error: Invalid appointment ID. It must be non-null and up to 10 characters.");
        }
        if (appointmentDate == null || appointmentDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Error: Invalid appointment date. It must not be in the past.");
        }
        if (appointmentDescription == null || appointmentDescription.length() > 50) {
            throw new IllegalArgumentException("Error: Invalid appointment description. It must be up to 50 characters.");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Error: Username cannot be empty.");
        }

        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentDescription = appointmentDescription;
        this.username = username;
    }

    // Getters and Setters
    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        if (appointmentID == null || appointmentID.length() > 10) {
            throw new IllegalArgumentException("Error: Invalid appointment ID. It must be non-null and up to 10 characters.");
        }
        this.appointmentID = appointmentID;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        if (appointmentDate == null || appointmentDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Error: Invalid appointment date. It must not be in the past.");
        }
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        if (appointmentDescription == null || appointmentDescription.length() > 50) {
            throw new IllegalArgumentException("Error: Invalid appointment description. It must be up to 50 characters.");
        }
        this.appointmentDescription = appointmentDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Error: Username cannot be empty.");
        }
        this.username = username;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    // Overrides for debugging
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(appointmentID, that.appointmentID) &&
               Objects.equals(appointmentDate, that.appointmentDate) &&
               Objects.equals(appointmentDescription, that.appointmentDescription) &&
               Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentID, appointmentDate, appointmentDescription, username);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID='" + appointmentID + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", appointmentDescription='" + appointmentDescription + '\'' +
                ", username='" + username + '\'' +
                ", version=" + version +
                '}';
    }
}
