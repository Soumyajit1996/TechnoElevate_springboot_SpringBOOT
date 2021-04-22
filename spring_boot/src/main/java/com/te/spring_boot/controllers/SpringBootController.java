package com.te.spring_boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring_boot.beans.EmployeeBean;
import com.te.spring_boot.beans.EmployeeResponse;
import com.te.spring_boot.service.IEmployeeService;

@RestController
public class SpringBootController {
	
	@Autowired
	IEmployeeService empService;
	
	@GetMapping(path="/")
	public String firstHandlerMethod() {
		return "TechnoElevate";
	}//end of firstHandlerMethod
	
	
	@GetMapping(path="/getEmp",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public EmployeeResponse getEmp(Integer id) {
		EmployeeResponse response=new EmployeeResponse();
		
		EmployeeBean empBean = empService.getEmployee(id);
		if(empBean!=null) {
			response.setStatus_code(200);
			response.setMessage("Success");
			response.setDescription("Data found for id "+id);
			response.setBean(empBean);
		}
		else {
			response.setStatus_code(400);
			response.setMessage("Failure");
			response.setDescription("Data Not found for id "+id);
		}
		return response;
	}
	
	@GetMapping(path="/getAll",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public EmployeeResponse getAll() {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeBean> empList= empService.getAllEmployee();
		if(empList!=null) {
			response.setStatus_code(200);
			response.setMessage("Success");
			response.setDescription("Data founded");
			response.setBeanList(empList);
		}
		else {
			response.setStatus_code(400);
			response.setMessage("Failure");
			response.setDescription("Something Went wrong");
		}
		return response;
	}
	
	@PostMapping(path="/addEmp",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public EmployeeResponse addEmp(@RequestBody EmployeeBean bean) {
		EmployeeResponse response = new EmployeeResponse();
		if(empService.addEmployee(bean)) {
			response.setStatus_code(200);
			response.setMessage("Success");
			response.setDescription("Employee Added");
		}
		else {
			response.setStatus_code(400);
			response.setMessage("Failure");
			response.setDescription("Not Added");
		}
		return response;
	}
	
	
	@DeleteMapping(path="/delete/{emp_id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public EmployeeResponse deleteEmp(@PathVariable(name="emp_id")int id) {
		EmployeeResponse response = new EmployeeResponse();
		if(empService.deleteEmp(id)) {
			response.setStatus_code(200);
			response.setMessage("Success");
			response.setDescription("Employee Deleted Successfully");
		}
		else {
			response.setStatus_code(400);
			response.setMessage("Failure");
			response.setDescription("Not Able to delete");
		}
		return response;
	}
	
	
	
	
	@PutMapping(path="/update" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
		public EmployeeResponse updateEmp(@RequestBody EmployeeBean bean) {
			EmployeeResponse response = new EmployeeResponse();
			if(empService.updateEmpdata(bean)) {
				response.setStatus_code(200);
				response.setMessage("Success");
				response.setDescription("Data updated");
			}
			else {
				response.setStatus_code(400);
				response.setMessage("Failure");
				response.setDescription("Somthing Went Wrong");
			}
			return response;
		}
}
