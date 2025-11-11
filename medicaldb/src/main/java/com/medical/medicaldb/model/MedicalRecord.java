package com.medical.medicaldb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Medical_Records")
public class MedicalRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MedicalRec_id")
    private Integer medicalRecId;

    //Relationships
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable=false)
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    //Medical Record Details
    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    @Column(name = "Diagnosis")
    private String diagnosis;

    @Column(name = "Symptoms", columnDefinition = "TEXT")
    private String symptoms;

    @Column(name = "Treatment", columnDefinition = "TEXT")
    private String treatment;

    @Column(name = "prescription", columnDefinition = "TEXT")
    private String prescription;

    @Column(name = "Notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    //Medical Record Constructors
    public MedicalRecord() {}

    public MedicalRecord(Patients patient, Doctor doctor, Appointment appointment, 
    String diagnosis, String symptoms, String treatment, String prescription, String notes){
        this.patient = patient;
        this.doctor = doctor;
        this.appointment = appointment;
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.prescription = prescription;
        this.notes = notes;
        this.visitDate = LocalDateTime.now();
    }

    //Getters and Setters
    public Integer getMedicalRecId(){
        return medicalRecId;
    }

    public void setMedicalRecID(Integer medicalRecId){
        this.medicalRecId = medicalRecId;
    }

    public Patients getPatient(){
        return patient;
    }

    public void setPatient(Patients patient){
        this.patient = patient;
    }

    public Doctor getDoctor(){
        return doctor;
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public Appointment getAppointment(){
        return appointment;
    }

    public void setAppointment(Appointment appointment){
        this.appointment = appointment;
    }

    public LocalDateTime getVisitDate(){
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate){
        this.visitDate = visitDate;
    }

    public String getDiagnosis(){
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }

    public String getSymptoms(){
        return symptoms;
    }

    public void setSymptoms(String symptoms){
        this.symptoms = symptoms;
    }

    public String getTreatment(){
        return treatment;
    }

    public void setTreatment(String treatment){
        this.treatment = treatment;
    }

    public String getPrescription(){
        return prescription;
    }

    public void setPrescription(String prescription){
        this.prescription = prescription;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

}
