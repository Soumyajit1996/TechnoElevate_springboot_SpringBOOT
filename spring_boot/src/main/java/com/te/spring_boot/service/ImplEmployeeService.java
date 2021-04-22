package com.te.spring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.spring_boot.beans.EmployeeBean;
import com.te.spring_boot.dao.IEmployeeDao;

@Service
public class ImplEmployeeService implements IEmployeeService{

	@Autowired
	IEmployeeDao empDao;
	
	@Override
	public EmployeeBean getEmployee(int id) {
		return empDao.getEmployee(id);
	}

	@Override
	public EmployeeBean authenticate(int id, String password) {
		return empDao.authenticate(id, password);
	}

	@Override
	public boolean deleteEmp(int id) {
		return empDao.deleteEmp(id);
	}

	@Override
	public boolean addEmployee(EmployeeBean bean) {
		return empDao.addEmployee(bean);
	}

	@Override
	public List<EmployeeBean> getAllEmployee() {
		return empDao.getAllEmployee();
	}

	@Override
	public boolean updateEmpdata(EmployeeBean bean) {
		return empDao.updateEmpdata(bean);
	}
}
