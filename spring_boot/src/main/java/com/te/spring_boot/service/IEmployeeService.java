package com.te.spring_boot.service;

import java.util.List;

import com.te.spring_boot.beans.EmployeeBean;

public interface IEmployeeService {
	public EmployeeBean getEmployee(int id);
	public EmployeeBean authenticate(int id,String password);
	public boolean deleteEmp(int id);
	public boolean addEmployee(EmployeeBean bean);
	public List<EmployeeBean> getAllEmployee();
	public boolean updateEmpdata(EmployeeBean bean);
}
