package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Add an appointment for a specific user
    public Appointment addAppointment(String username, Appointment appointment) {
        appointment.setUsername(username); // Ensures the appointment is assigned to the user
        return appointmentRepository.save(appointment);
    }

    // Get all appointments for a specific user
    public List<Appointment> getAppointmentsByUsername(String username) {
        return appointmentRepository.findByUsername(username);
    }

    // Get appointment by ID (check if it belongs to the user)
    public Appointment getAppointmentById(String username, String appointmentID) {
        Optional<Appointment> appointment = appointmentRepository.findByAppointmentID(appointmentID);
        if (appointment.isPresent() && appointment.get().getUsername().equals(username)) {
            return appointment.get();
        }
        throw new IllegalArgumentException("Error: Unauthorized access or appointment does not exist.");
    }

    // Update appointment date (only if the user owns the appointment)
    public Appointment updateAppointmentDate(String username, String appointmentID, LocalDate appointmentDate) {
        Appointment appointment = getAppointmentById(username, appointmentID);
        appointment.setAppointmentDate(appointmentDate);
        return appointmentRepository.save(appointment);
    }

    // Update appointment description (only if the user owns the appointment)
    public Appointment updateAppointmentDescription(String username, String appointmentID, String description) {
        Appointment appointment = getAppointmentById(username, appointmentID);
        appointment.setAppointmentDescription(description);
        return appointmentRepository.save(appointment);
    }

    // Delete appointment (only if the user owns it)
    @Transactional
    public void deleteAppointment(String username, String appointmentID) {
        Appointment appointment = getAppointmentById(username, appointmentID);
        appointmentRepository.delete(appointment);
    }
}
