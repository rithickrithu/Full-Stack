package com.atdxt.spring.boot.crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EmployeeDetails")

public class EmployeeDetails {
    @Id
    @Column(name ="id", length =50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name ="department", length =50)
    private String department;
    @Column(name ="position", length =50)
    private String position;
    @Column(name ="salary", length =50)
    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
