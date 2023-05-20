package com.employee.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.excel.UserExcelExporter;
import com.employee.pdf.PDFGenerator;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService es;
	@Autowired
	EmployeeRepository er;

	@PostMapping("/setAllEmployeeDetails")
	public String setAllEmployeeDetails(@RequestBody List<Employee> emp) {
		
		return es.setAllEmployeeDetails(emp);
	}
	@GetMapping("getAllDetails")
	public List<Employee> getAllDetails(){
		
		return es.getAllDetails();
	}

	@GetMapping("excel")
	
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<Employee> listUsers = er.findAll();
		
		UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

		excelExporter.export(response);
	}
	@GetMapping("pdf")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		
		List<Employee> employeeList = er.findAll();
		
		PDFGenerator generator = new PDFGenerator();
		generator.setEmployeeList(employeeList);
		generator.generate(response);
		
	}

}
