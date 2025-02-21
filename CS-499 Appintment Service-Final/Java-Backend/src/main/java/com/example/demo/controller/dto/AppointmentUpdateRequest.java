package com.example.demo.controller.dto;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) for updating an appointment.
 * Contains fields for updating the appointment date and description.
 */
public class AppointmentUpdateRequest {
    private LocalDate date;
    private String description;

    // Getter for date field
    public LocalDate getDate() {
        return date;
    }

    // Setter for date field
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter for description field
    public String getDescription() {
        return description;
    }

    // Setter for description field
    public void setDescription(String description) {
        this.description = description;
    }
}
