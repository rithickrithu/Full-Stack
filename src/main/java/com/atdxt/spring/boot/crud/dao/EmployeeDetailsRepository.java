package com.atdxt.spring.boot.crud.dao;

import com.atdxt.spring.boot.crud.entity.Employee;
import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Integer> {
}
