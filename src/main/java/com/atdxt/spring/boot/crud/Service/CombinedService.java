package com.atdxt.spring.boot.crud.Service;

import com.atdxt.spring.boot.crud.Repository.EmployeeDetailsRepository;
import com.atdxt.spring.boot.crud.Repository.EmployeeRepository;
import com.atdxt.spring.boot.crud.entity.Employee;
import com.atdxt.spring.boot.crud.entity.EmployeeDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CombinedService {


    private final EmployeeRepository employeeRepository;
    @Autowired
    public CombinedService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

//    @Autowired
//    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    public List<Object> getAllEntities() {
//        List<Object> combinedEntities = Collections.singletonList(employeeRepository.findAllWithDetails());
//        return combinedEntities;
//    }
public List<Employee> getAlluser() {
    try {
        return employeeRepository.findAll();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}



    public List<Object> getEntityById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Optional<EmployeeDetails> employeeDetails = employeeDetailsRepository.findById(id);

        List<Object> combinedEntityList = new ArrayList<>();

        employee.ifPresent(combinedEntityList::add);
        employeeDetails.ifPresent(combinedEntityList::add);

        return combinedEntityList;
    }

    public void updateEntity(Integer id, Map<String, Object> updatedEntityMap) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        existingEmployee.ifPresent(employee -> {
            Employee updatedEmployee = objectMapper.convertValue(updatedEntityMap.get("employee1"), Employee.class);
            employee.setName(updatedEmployee.getName());
            employee.setAge(updatedEmployee.getAge());
            employee.setPhoneNo(updatedEmployee.getPhoneNo());
            employee.setCity(updatedEmployee.getCity());
            employeeRepository.save(employee);
        });

        Optional<EmployeeDetails> existingEmployeeDetails = employeeDetailsRepository.findById(id);

        existingEmployeeDetails.ifPresent(employeeDetails -> {
            EmployeeDetails updatedEmployeeDetails = objectMapper.convertValue(updatedEntityMap.get("EmployeeDetails"), EmployeeDetails.class);
            employeeDetails.setDepartment(updatedEmployeeDetails.getDepartment());
            employeeDetails.setPosition(updatedEmployeeDetails.getPosition());
            employeeDetails.setSalary(updatedEmployeeDetails.getSalary());
            employeeDetailsRepository.save(employeeDetails);
        });
    }

//    public String saveEntity(Map<String, Object> entityMap) {
//        try {
//            if (entityMap.containsKey("employee1")) {
//                Employee employee = objectMapper.convertValue(entityMap.get("employee1"), Employee.class);
//                employeeRepository.save(employee);
//            }
//
//            if (entityMap.containsKey("EmployeeDetails")) {
//                EmployeeDetails employeeDetails = objectMapper.convertValue(entityMap.get("EmployeeDetails"), EmployeeDetails.class);
//                employeeDetailsRepository.save(employeeDetails);
//            }
//
//            return "Entities saved successfully";
//        } catch (Exception ex) {
//            return "Error saving entities: " + ex.getMessage();
//        }
//    }

    public String saveEntity(Map<String, Object> entityMap) {
        try {
            if (entityMap.containsKey("employee1") && entityMap.containsKey("EmployeeDetails")) {
                Employee employee = objectMapper.convertValue(entityMap.get("employee1"), Employee.class);
                EmployeeDetails employeeDetails = objectMapper.convertValue(entityMap.get("EmployeeDetails"), EmployeeDetails.class);

                // Set the relationship bidirectionally
                employee.setEmployeeDetails(employeeDetails);
                employeeDetails.setEmployee(employee);

                // Save the entities
                employeeRepository.save(employee);

                return "Entities saved successfully";
            } else {
                return "Error: Both employee1 and EmployeeDetails must be present in the input map.";
            }
        } catch (Exception ex) {
            return "Error saving entities: " + ex.getMessage();
        }
    }


    public void deleteEntityById(Integer id) {
        // Delete EmployeeDetails first to avoid foreign key constraint issues
        Optional<EmployeeDetails> employeeDetailsOptional = employeeDetailsRepository.findById(id);
        employeeDetailsOptional.ifPresent(employeeDetailsRepository::delete);

        // Delete Employee
        employeeRepository.deleteById(id);
    }


}
