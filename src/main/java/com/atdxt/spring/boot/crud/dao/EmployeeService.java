package com.atdxt.spring.boot.crud.dao;

import com.atdxt.spring.boot.crud.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public String saveEmployee(Employee employee) {
        try {

            employeeRepository.save(employee);
            return "Employee saved successfully";
        } catch (Exception ex) {
            return "Error saving employee: " + ex.getMessage();
        }
    }

    public void updateEmployee(int id, Employee updatedEmployee) {
        updatedEmployee.setId(id);

        employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }
}
