package com.medical.medicaldb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Doctors")
public class Doctor {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDoctors")
    private Integer id;

    @Column(name = "Doc_Name")
    private String name;

    @Column(name = "Doc_Gender")
    private String gender;

    @Column(name = "Specialization")
    private String specialization;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "department_id")
    private Integer D_id;

    //Getters and Setters
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    public String getSpecialization() {return specialization;}
    public void setSpecialization(String specialization) {this.specialization = specialization;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public Integer getDept_id() {return D_id;}
    public void setDept_id(Integer D_id) {this.D_id = D_id;}
    
}
