package com.example.SpringBase.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail (String email); //создам поиск по email, для того, чтобы в БД не попали пользователи с одинаковыми email
    Optional<Employee> findById (Long id);
}
