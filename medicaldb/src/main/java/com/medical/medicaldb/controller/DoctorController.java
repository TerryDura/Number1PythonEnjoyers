package com.medical.medicaldb.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.medical.medicaldb.model.Doctor;
import com.medical.medicaldb.repository.DoctorRepository;

import io.micrometer.common.lang.NonNull;




@RestController
@RequestMapping("/api/doctors")

public class DoctorController {
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    //Get all Docs
    @GetMapping("/view-all")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    //Get doctor by ID
    @SuppressWarnings("null")
    @NonNull
    @GetMapping("/search/{id}")
    public Doctor getDoctorById(@PathVariable Integer id){
        return doctorRepository.findById(id).orElse(null);
    }

    //Add new doctor
    @SuppressWarnings("null")
    @PostMapping ("/add")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }

    //Update Doctor
    @SuppressWarnings("null")
    @PutMapping("/update/{id}")
    public Doctor updateDoctor(@PathVariable Integer id, @RequestBody Doctor updatedDoctor){
        return doctorRepository.findById(id) 
            .map(doctor -> {
                doctor.setName(updatedDoctor.getName());
                doctor.setGender(updatedDoctor.getGender());
                doctor.setSpecialization(updatedDoctor.getSpecialization());
                doctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
                doctor.setEmail(updatedDoctor.getEmail());
                doctor.setDept_id(updatedDoctor.getDept_id());
                return doctorRepository.save(doctor);

            })
            .orElseGet(() -> {
                updatedDoctor.setId(id);
                return doctorRepository.save(updatedDoctor);
            });
    }

    //Delete Doctor
    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public void deleteDoctor(@PathVariable Integer id){
        doctorRepository.deleteById(id);
    }
}
