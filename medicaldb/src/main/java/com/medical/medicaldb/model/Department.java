package com.medical.medicaldb.model;


import jakarta.persistence.*;


@Entity
@Table(name = "Departments")

public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Dept_id")
    private Integer deptId;

    @Column(name = "Dept_name", nullable = false, unique = true, length = 50)
    private String deptName;

    @Column(name = "Dept_code", unique = true, length = 10)
    private String deptCode;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    

    //Constructors
    public Department(){}

    public Department(String deptName, String deptCode, String phoneNumber, String email){
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //Getters and Setters
    public Integer getDeptId(){
        return deptId;
    }

    public void setDeptId(Integer deptId){
        this.deptId = deptId;
    }

    public String getDeptName(){
        return deptName;
    }

    public void setDeptName(String deptName){
        this.deptName = deptName;
    }

    public String getDeptCode(){
        return deptCode;
    }

    public void setDeptCode(String deptCode){
        this.deptCode = deptCode;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


}
