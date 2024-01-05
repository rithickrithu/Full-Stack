package com.atdxt.spring.boot.crud.dao;

import com.atdxt.spring.boot.crud.entity.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> allEmployee() {
        return jdbcTemplate.query("SELECT * FROM employee ORDER BY id ASC", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public void deleteByID(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        jdbcTemplate.update(sql, id);
    }


    @Override
//    public void updateEmployee(Employee employee) {
//        StringBuilder sql = new StringBuilder("UPDATE employee SET ");
//
//        List<Object> params = new ArrayList<>();
//
//        if (employee.getName() != null) {
//            sql.append("name=?, ");
//            params.add(employee.getName());
//        }
//
//        if (employee.getAge() != 0) {
//            sql.append("age=?, ");
//            params.add(employee.getAge());
//        }
//
//        if (employee.getPhoneNo() != 0) {
//            sql.append("phoneNo=?, ");
//            params.add(employee.getPhoneNo());
//        }
//
//        if (employee.getCity() != null) {
//            sql.append("city=?, ");
//            params.add(employee.getCity());
//        }

        // Remove the trailing comma and space
//        sql.delete(sql.length() - 2, sql.length());
//
//        sql.append(" WHERE id=?");
//        params.add(employee.getId());INSERT
//
//        jdbcTemplate.update(sql.toString(), params.toArray());
//    }

   public void updateEmployee(Employee employee) {
      String sql = "UPDATE employee SET name=?, age=?, phoneNo=?, city=?, updatedTime=CURRENT_TIMESTAMP  WHERE id=?";
      jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getPhoneNo(), employee.getCity(),employee.getId());


    }

    @Override
    public Employee getByID(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id=?", new BeanPropertyRowMapper<>(Employee.class), id);
    }

    @Override
    public String saveEmployee(Employee e) {
        // Use try-catch block to handle exceptions
        try {
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            jdbcTemplate.update("INSERT INTO employee (name, age, phoneNo, city,createdTime) VALUES (?, ?, ?, ?,?)",
                    e.getName(), e.getAge(), e.getPhoneNo(), e.getCity(),currentTimestamp);
            return "Employee saved successfully";
        } catch (Exception ex) {
            // Return an error message if the insertion fails
            return "Error saving employee: " + ex.getMessage();
        }


    }
}

