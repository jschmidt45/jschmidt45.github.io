package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.controller.dto.AppointmentUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Create an appointment for the logged-in user
    @PostMapping("/{username}")
    public Appointment createAppointment(@PathVariable String username, @RequestBody Appointment appointment) {
        return appointmentService.addAppointment(username, appointment);
    }

    // Get only the logged-in user's appointments
    @GetMapping("/user/{username}")
    public List<Appointment> getAppointmentsByUser(@PathVariable String username) {
        return appointmentService.getAppointmentsByUsername(username);
    }

    // Get a specific appointment only if it belongs to the user
    @GetMapping("/{username}/{appointmentID}")
    public Appointment getAppointmentById(@PathVariable String username, @PathVariable String appointmentID) {
        return appointmentService.getAppointmentById(username, appointmentID);
    }

    // Update appointment's date if owned by user
    @PutMapping("/{username}/{appointmentID}/date")
    public Appointment updateAppointmentDate(
            @PathVariable String username,
            @PathVariable String appointmentID,
            @RequestBody AppointmentUpdateRequest updateRequest) {
        return appointmentService.updateAppointmentDate(username, appointmentID, updateRequest.getDate());
    }

    // Update appointment's description if owned by user
    @PutMapping("/{username}/{appointmentID}/description")
    public Appointment updateAppointmentDescription(
            @PathVariable String username,
            @PathVariable String appointmentID,
            @RequestBody AppointmentUpdateRequest updateRequest) {
        return appointmentService.updateAppointmentDescription(username, appointmentID, updateRequest.getDescription());
    }

    // Delete an appointment if owned by user
    @DeleteMapping("/{username}/{appointmentID}")
    public void deleteAppointment(@PathVariable String username, @PathVariable String appointmentID) {
        appointmentService.deleteAppointment(username, appointmentID);
    }
}
