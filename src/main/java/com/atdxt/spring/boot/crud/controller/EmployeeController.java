package com.atdxt.spring.boot.crud.controller;

import com.atdxt.spring.boot.crud.dao.EmployeeRepository;
import com.atdxt.spring.boot.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/user")
    public String saveEmployee(@RequestBody Employee e) {
        employeeRepository.save(e);
        return "Employee saved successfully";
    }

    @GetMapping("/user/{id}")
    public Employee getByID(@PathVariable int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping("/users")
    public List<Employee> allEmployees() {
        return employeeRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        updatedEmployee.setId(id);
        employeeRepository.save(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByID(@PathVariable int id) {
        employeeRepository.deleteById(id);
    }
}
