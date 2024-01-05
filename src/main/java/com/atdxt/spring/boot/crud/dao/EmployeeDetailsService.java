package com.atdxt.spring.boot.crud.dao;

import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class EmployeeDetailsService {
    @Autowired
    private EmployeeDetailsRepository employeeRepository;

    public List<EmployeeDetails> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeDetails getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public String saveEmployee(EmployeeDetails employee) {
        try {

            employeeRepository.save(employee);
            return "Employee saved successfully";
        } catch (Exception ex) {
            return "Error saving employee: " + ex.getMessage();
        }
    }

    public void updateEmployee(int id, EmployeeDetails updatedEmployee) {
        updatedEmployee.setId(id);

        employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }
}



