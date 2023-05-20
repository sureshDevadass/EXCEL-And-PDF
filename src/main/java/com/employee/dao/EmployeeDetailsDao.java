package com.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Repository
public class EmployeeDetailsDao {

@Autowired
EmployeeRepository er;
	
	public String setAllEmployeeDetails(List<Employee> emp) {
		er.saveAll(emp);
		return "Employees Data Added";
	}

	public List<Employee> getAllDetails() {
		
		return er.findAll();
	}

}
