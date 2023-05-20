package com.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeDetailsApplication {
	@Autowired
	EmployeeRepository er;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailsApplication.class, args);
	}

	public void run(String... args)throws Exception {
		for (int i = 0; i < 11; i++) {
			Employee student = new Employee();
			student.setEmpId(i);
			student.setEmployeeName("Employee Name " + i);
			er.save(student);
		}
	}
}