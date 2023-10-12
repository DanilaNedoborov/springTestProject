package com.example.SpringBase.services;

import com.example.SpringBase.employee.Employee;
import com.example.SpringBase.employee.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
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
    public void updateEmployee(Long id, String name, String email){
        if(employeeRepo.findById(id).isEmpty()){
            throw new IllegalStateException("Employee is id not found");
        }
        Employee employee = employeeRepo.findById(id).get();

        if(name != null && name.length() > 0 && !Objects.equals(name, employee.getName())){
            employee.setName(name);
            employeeRepo.save(employee);
        }

        if(email != null && email.length() > 0 && !Objects.equals(email, employee.getEmail())){
            Optional<Employee> employeeOptionalEmail = employeeRepo.findByEmail(email);
            if (employeeOptionalEmail.isPresent()){
                throw new IllegalStateException("This email is already exist");
            }
            employee.setEmail(email);
            employeeRepo.save(employee);
        }
    }
}
