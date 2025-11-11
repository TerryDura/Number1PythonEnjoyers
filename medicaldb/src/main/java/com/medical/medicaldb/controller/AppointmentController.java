package com.medical.medicaldb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.medical.medicaldb.model.Appointment;
import com.medical.medicaldb.repository.AppointmentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")

public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
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
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentRepository.save(appointment);
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
