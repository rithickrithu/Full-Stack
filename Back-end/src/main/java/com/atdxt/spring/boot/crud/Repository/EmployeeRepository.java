package com.atdxt.spring.boot.crud.Repository;

import com.atdxt.spring.boot.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.employeeDetails")
    List<Employee> findAllWithDetailsOrderById();
}
