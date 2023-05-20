package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeDetailsDao;
import com.employee.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDetailsDao eds;

	public String setAllEmployeeDetails(List<Employee> emp) {
		return eds.setAllEmployeeDetails(emp);
		
		
	}

	public List<Employee> getAllDetails() {
		
		return eds.getAllDetails();
	}
}
