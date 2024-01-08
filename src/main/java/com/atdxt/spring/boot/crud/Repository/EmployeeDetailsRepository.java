package com.atdxt.spring.boot.crud.Repository;

import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Integer> {
}
