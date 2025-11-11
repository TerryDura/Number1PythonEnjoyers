package com.medical.medicaldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medical.medicaldb.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>  {}
