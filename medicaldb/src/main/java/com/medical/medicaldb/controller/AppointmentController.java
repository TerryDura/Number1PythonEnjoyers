package com.medical.medicaldb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;


import com.medical.medicaldb.model.Appointment;
import com.medical.medicaldb.repository.AppointmentRepository;
import com.medical.medicaldb.repository.DoctorRepository;
import com.medical.medicaldb.repository.PatientRepository;
import com.medical.medicaldb.dto.AppointmentDTO;
import com.medical.medicaldb.model.Patients;
import com.medical.medicaldb.model.Doctor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")

public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentController(AppointmentRepository appointmentRepository,
    PatientRepository patientRepository,
    DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // View all appointments
    @GetMapping("/view-all")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();

    }

    // View a Single appointment
    @SuppressWarnings("null")
    @GetMapping("/search/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Integer id) {
        return appointmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Create new appointment
    @SuppressWarnings("null")
    @PostMapping("/add")
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO dto){
        Appointment appt = new Appointment();

        appt.setAppointmentDate(LocalDate.parse(dto.appointmentDate));
        appt.setStartTime(LocalTime.parse(dto.startTime));
        appt.setEndTime(LocalTime.parse(dto.endTime));
        appt.setReason(dto.reason);
        

        Patients patient = patientRepository.findById(dto.patientId)
                            .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(dto.doctorId)
                            .orElseThrow(() -> new RuntimeException("Doctor not found."));
        
        
        appt.setPatient(patient);
        appt.setDoctor(doctor);
        appt.setStatus("Scheduled");

        Appointment saved = appointmentRepository.save(appt);
        return ResponseEntity.ok(saved);

    }

    @SuppressWarnings("null")
    @PutMapping("update/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment updatedAppointment){
        return appointmentRepository.findById(id)
                .map(existing -> {
                    existing.setAppointmentDate(updatedAppointment.getAppointmentDate());
                    existing.setStartTime(updatedAppointment.getStartTime());
                    existing.setEndTime(updatedAppointment.getEndTime());
                    existing.setReason(updatedAppointment.getReason());
                    existing.setStatus(updatedAppointment.getStatus());
                    existing.setDoctor(updatedAppointment.getDoctor());
                    existing.setPatient(updatedAppointment.getPatient());
                    return ResponseEntity.ok(appointmentRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    //Delete Appointment
    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id){
        if (!appointmentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        appointmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
