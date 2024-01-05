package com.atdxt.spring.boot.crud.controller;

import com.atdxt.spring.boot.crud.dao.EmployeeDetailsRepository;
import com.atdxt.spring.boot.crud.dao.EmployeeDetailsService;
import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api1")

public class EmployeeDetailsController {
    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @PostMapping("/user1")
    public String saveEmployee(@RequestBody EmployeeDetails e) {
        employeeDetailsRepository.save(e);
        return "Employee saved successfully";
    }

    @GetMapping("/user1/{id}")
    public EmployeeDetails getByID(@PathVariable int id) {
        return employeeDetailsRepository.findById(id).orElse(null);
    }

    @GetMapping("/users1")
    public List<EmployeeDetails> allEmployees() {
        return employeeDetailsRepository.findAll();
    }

    @PutMapping("/update1/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody EmployeeDetails updatedEmployee) {
        updatedEmployee.setId(id);
        employeeDetailsRepository.save(updatedEmployee);
    }

    @DeleteMapping("/delete1/{id}")
    public void deleteByID(@PathVariable int id) {
        employeeDetailsRepository.deleteById(id);
    }
}
