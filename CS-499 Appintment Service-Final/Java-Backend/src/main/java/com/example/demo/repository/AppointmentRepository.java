package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    boolean existsByAppointmentID(String appointmentID); 

    Optional<Appointment> findByAppointmentID(String appointmentID);  

    void deleteByAppointmentID(String appointmentID);  

    List<Appointment> findByUsername(String username);  // Get appointments by username
}
