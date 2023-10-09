package com.example.SpringBase;

import com.example.SpringBase.employee.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class SpringBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBaseApplication.class, args);
	}

}
