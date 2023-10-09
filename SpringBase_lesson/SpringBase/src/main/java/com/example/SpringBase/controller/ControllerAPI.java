package com.example.SpringBase.controller;

import com.example.SpringBase.employee.Employee;
import com.example.SpringBase.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ControllerAPI {

    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployee(){
        return employeeService.employeeList();
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
}
