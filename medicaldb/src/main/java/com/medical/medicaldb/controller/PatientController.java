package com.medical.medicaldb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.medical.medicaldb.model.Patients;
import com.medical.medicaldb.repository.PatientRepository;


@RestController
@RequestMapping("/api/patients")

public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Get all patients
    @GetMapping("/view-all")
    public List<Patients> getAllPatients() {
        return patientRepository.findAll();
    }

    //Get patient by ID
    @SuppressWarnings("null")
    @GetMapping("/search/{id}")
    public ResponseEntity<Patients> getPatientById(@PathVariable Integer id){
        return patientRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Add new patient(POST)
    @SuppressWarnings("null")
    @PostMapping("/add")
    public Patients createpatient(@RequestBody Patients patient){
        return patientRepository.save(patient);
    }
    
    //Update Patient (Put)
    @SuppressWarnings("null")
    @PutMapping("/update/{id}")
    public ResponseEntity<Patients> updatePatient(@PathVariable Integer id, @RequestBody Patients updatedPatient){
        return patientRepository.findById(id)
            .map(patient -> {
                patient.setFullName(updatedPatient.getFullName());
                patient.setGender(updatedPatient.getGender());
                patient.setDob(updatedPatient.getDob());
                patient.setPhoneNumber(updatedPatient.getPhoneNumber());
                patient.setEmail(updatedPatient.getEmail());
                patient.setBloodType(updatedPatient.getBloodType());
                patient.setInsurance(updatedPatient.getInsurance());
                return ResponseEntity.ok(patientRepository.save(patient));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete Patient
    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id){
        if(!patientRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}