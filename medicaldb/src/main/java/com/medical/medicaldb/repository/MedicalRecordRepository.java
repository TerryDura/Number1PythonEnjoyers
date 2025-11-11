package com.medical.medicaldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medical.medicaldb.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer>{}
