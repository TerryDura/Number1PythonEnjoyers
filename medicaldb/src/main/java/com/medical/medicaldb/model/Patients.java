package com.medical.medicaldb.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Patients")

public class Patients {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer id;

    @Column(name = "Full_Name", nullable = false)
    private String fullName;

    @Column(name = "Pat_Gender")
    private String gender; 

    @Column(name = "DoB")
    private LocalDate dob;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "Pat_Insurance")
    private String insurance;

    //Getters and Setters

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    
    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}
    
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    public LocalDate getDob() {return dob;}
    public void setDob(LocalDate dob) {this.dob = dob;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getBloodType() {return bloodType;}
    public void setBloodType(String bloodType) {this.bloodType = bloodType;}

    public String getInsurance() {return insurance;}
    public void setInsurance(String insurance) {this.insurance = insurance;}


}
