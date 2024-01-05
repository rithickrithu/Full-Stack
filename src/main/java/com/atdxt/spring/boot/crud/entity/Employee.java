package com.atdxt.spring.boot.crud.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity

//@Getter
//@Setter
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee1")
public class Employee {
    @Id
    @Column(name="id", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;
    @Column(name="name", length = 50)
    private String name;
    @Column(name="age", length = 50)
    private int age;
    @Column(name="phoneNo", length = 50)
    private int phoneNo;
    @Column(name="city", length = 50)
    private String city;
    @Column(name="createdTime", length = 50)
    private Timestamp createdTime;
    @Column(name="updatedTime", length = 50)
    private Timestamp updatedTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = Timestamp.from(Instant.now());
    }


    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



}
