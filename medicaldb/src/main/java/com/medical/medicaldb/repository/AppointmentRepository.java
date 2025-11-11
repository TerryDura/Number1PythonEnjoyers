package com.medical.medicaldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medical.medicaldb.model.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    
}
