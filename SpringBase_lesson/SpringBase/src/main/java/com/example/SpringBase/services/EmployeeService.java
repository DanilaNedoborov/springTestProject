package com.example.SpringBase.services;

import com.example.SpringBase.employee.Employee;
import com.example.SpringBase.employee.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    EmployeeRepo employeeRepo;

    public List<Employee> employeeList (){
        return employeeRepo.findAll();
    }

    public Employee saveEmployee (Employee employee){
        Optional<Employee> employeeOptional = employeeRepo.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent()){
            throw new IllegalStateException("This email is already exist");
        }
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (employeeOptional.isEmpty()){
            throw new IllegalStateException("the is no employee with this ID");
        }
        employeeRepo.deleteById(id);
    }
}
