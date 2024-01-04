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
    public String saveEmployee(@RequestBody Employee e){
        return employeeRepository.saveEmployee(e);
    }
    @GetMapping("/user/{id}")
    public Employee getByID(@PathVariable int id){
        return employeeRepository.getByID(id);

    }
    @GetMapping("/users")
    public List<Employee> allEmployees(){
        return employeeRepository.allEmployee();
    }

   @PutMapping("/update/{id}")
   public void updateEmployee( @PathVariable int id,@RequestBody Employee updatedEmployee) {
        updatedEmployee.setId(id);
        employeeRepository.updateEmployee(updatedEmployee);
  }






    @DeleteMapping("/delete/{id}")
    public void deleteByID(@PathVariable int id){
         employeeRepository.deleteByID(id);
    }



}