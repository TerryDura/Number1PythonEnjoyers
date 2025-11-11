package com.medical.medicaldb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.medical.medicaldb.model.MedicalRecord;
import com.medical.medicaldb.repository.MedicalRecordRepository;


@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordController(MedicalRecordRepository medicalRecordRepository){
        
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @GetMapping("/view-all")
    public List<MedicalRecord> getAllRecords(){
        return medicalRecordRepository.findAll();
    }

    @SuppressWarnings("null")
    @GetMapping("/search/{id}")
    public ResponseEntity<MedicalRecord> getById(@PathVariable Integer id){
        Optional<MedicalRecord> record = medicalRecordRepository.findById(id);
        return record.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("null")
    @PostMapping("/add")
    public ResponseEntity<MedicalRecord> addRecord(@RequestBody MedicalRecord record){
        return ResponseEntity.ok(medicalRecordRepository.save(record));
    }

    @SuppressWarnings("null")
    @PutMapping("/update/{id}")
    public ResponseEntity<MedicalRecord> updateRecord(@PathVariable Integer id, @RequestBody MedicalRecord updated){
        return medicalRecordRepository.findById(id)
        .map(existing -> {
            existing.setDiagnosis(updated.getDiagnosis());
            existing.setSymptoms(updated.getSymptoms());
            existing.setTreatment(updated.getTreatment());
            existing.setPrescription(updated.getPrescription());
            existing.setNotes(updated.getNotes());
            existing.setDoctor(updated.getDoctor() != null ? updated.getDoctor() : existing.getDoctor());
            existing.setAppointment(updated.getAppointment() != null ? updated.getAppointment() : existing.getAppointment());
            MedicalRecord saved = medicalRecordRepository.save(existing);
            return ResponseEntity.ok(saved);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer id){
        if (!medicalRecordRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        medicalRecordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
