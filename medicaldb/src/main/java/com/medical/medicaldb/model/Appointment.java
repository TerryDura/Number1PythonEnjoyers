package com.medical.medicaldb.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments" )
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "Patient_id")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "idDoctors" )
    private Doctor doctor;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "reason")
    private String reason;

    @Column(name = "appoint_status")
    private String status; //Enum --> handled as string: Scheduled, Completed, Cancelled, No show

    @Column(name = "created_at", insertable = false, updatable = false) 
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = true, updatable = true) 
    private LocalDateTime updatedAt;

    //Getters and Setters

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public Patients getPatient() {return patient;}
    public void setPatient(Patients patient) {this.patient = patient;}

    public Doctor getDoctor() {return doctor;}
    public void setDoctor(Doctor doctor) {this.doctor = doctor;}

    public LocalDate getAppointmentDate() {return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) {this.appointmentDate = appointmentDate;}

    public LocalTime getStartTime() {return startTime;}
    public void setStartTime(LocalTime startTime) {this.startTime = startTime;}

    public LocalTime getEndTime() {return endTime;}
    public void setEndTime(LocalTime endTime) {this.endTime = endTime;}

    public String getReason() {return reason;}
    public void setReason(String reason) {this.reason = reason;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}



}
