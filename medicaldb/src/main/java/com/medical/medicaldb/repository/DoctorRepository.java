package com.medical.medicaldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medical.medicaldb.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    
}
