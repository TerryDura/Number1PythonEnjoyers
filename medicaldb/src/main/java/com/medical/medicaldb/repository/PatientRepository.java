package com.medical.medicaldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medical.medicaldb.model.Patients;
public interface PatientRepository extends JpaRepository<Patients, Integer>{

}
