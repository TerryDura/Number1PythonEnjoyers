package com.medical.medicaldb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


import com.medical.medicaldb.model.Department;
import com.medical.medicaldb.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/departments")

public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Gather all departments
    @GetMapping("/view-all")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get Department by ID
    @SuppressWarnings("null")
    @GetMapping("/search/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
        Optional<Department> dept = departmentRepository.findById(id);
        return dept.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create New Department
    @SuppressWarnings("null")
    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department saved = departmentRepository.save(department);
        return ResponseEntity.ok(saved);

    }

    // Update Existing Department
    @SuppressWarnings("null")
    @PutMapping("update/{id}")
    public ResponseEntity<Department> updateDepartment(
        @PathVariable Integer id, 
        @RequestBody Department updatedDept) {
        return departmentRepository.findById(id)
                .map(dept -> {
                    dept.setDeptName(updatedDept.getDeptName() != null ? updatedDept.getDeptName() : dept.getDeptName());
                    dept.setDeptCode(updatedDept.getDeptCode() != null ? updatedDept.getDeptCode() : dept.getDeptCode());
                    dept.setPhoneNumber(updatedDept.getPhoneNumber() != null ? updatedDept.getPhoneNumber() : dept.getPhoneNumber());
                    dept.setEmail(updatedDept.getEmail() != null ? updatedDept.getEmail() : dept.getEmail());
                    Department saved = departmentRepository.save(dept);
                    return ResponseEntity.ok(saved);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id){
        if (!departmentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        departmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
