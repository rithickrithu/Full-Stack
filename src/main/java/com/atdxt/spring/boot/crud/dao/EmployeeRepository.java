package com.atdxt.spring.boot.crud.dao;

import com.atdxt.spring.boot.crud.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository {
    String saveEmployee(Employee e);
    Employee getByID(int id);
    List<Employee> allEmployee();

    void deleteByID(int id);


     void updateEmployee(Employee employee);
}
