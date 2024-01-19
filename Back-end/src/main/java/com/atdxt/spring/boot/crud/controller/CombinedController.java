package com.atdxt.spring.boot.crud.controller;

import com.atdxt.spring.boot.crud.Service.CombinedService;
import com.atdxt.spring.boot.crud.entity.Employee;
import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://ec2-54-253-49-79.ap-southeast-2.compute.amazonaws.com:3000")
@RequestMapping("/api")
public class CombinedController {

    @Autowired
    private CombinedService combinedService;

    @PostMapping("/save")
    public String saveEntity(@RequestBody Map<String, Object> entityMap) {
        return combinedService.saveEntity(entityMap);
    }

    @GetMapping("/get/{id}")
    public List<Object> getEntityById(@PathVariable Integer id) {
        return combinedService.getEntityById(id);
    }

    @GetMapping("/get-all")
    public List<Employee> getAllEntities() {
        return combinedService.getAllEntitiesOrderedById();
    }
    @PutMapping("/update/{id}")
    public void updateEntity(@PathVariable Integer id, @RequestBody Map<String, Object> updatedEntity) {
        combinedService.updateEntity(id, updatedEntity);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEntityById(@PathVariable Integer id) {
        combinedService.deleteEntityById(id);
    }

}

