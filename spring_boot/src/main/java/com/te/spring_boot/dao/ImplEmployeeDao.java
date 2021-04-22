package com.te.spring_boot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.spring_boot.beans.EmployeeBean;

@Repository
public class ImplEmployeeDao implements IEmployeeDao{
	
	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public EmployeeBean getEmployee(int id) {
		EntityManager manager = factory.createEntityManager();
		EmployeeBean employeeInfo = manager.find(EmployeeBean.class, id);
		return employeeInfo;
	}

	@Override
	public EmployeeBean authenticate(int id, String password) {
		EntityManager manager = factory.createEntityManager();

		EmployeeBean bean = manager.find(EmployeeBean.class, id);
		if (bean != null) {
			if (bean.getPassword().equals(password)) {
				System.out.println("Login Successfully");
				return bean;
			} else {
				System.out.println("Invalid Credential");
				return null;
			}
		} else {
			System.out.println("Invalid Credential");
			return null;
		}
	}

	@Override
	public boolean deleteEmp(int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		EmployeeBean bean = manager.find(EmployeeBean.class, id);
		manager.remove(bean);
		transaction.commit();
		if (bean != null) {
			System.out.println("Deleted Successfully");
			return true;
		} else {
			System.out.println("Not able to delete");
			return false;
		}
	}

	@Override
	public boolean addEmployee(EmployeeBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isAdded = false;
		try {
			transaction.begin();
			manager.persist(bean);
			isAdded = true;
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			isAdded = false;
			e.printStackTrace();
		}
		return isAdded;
	}

	@Override
	public List<EmployeeBean> getAllEmployee() {
		EntityManager manager = factory.createEntityManager();
		String data = "from EmployeeBean";
		Query query = manager.createQuery(data);

		List<EmployeeBean> empList = query.getResultList();
		return empList;
	}

	@Override
	public boolean updateEmpdata(EmployeeBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isUpdate = false;
		try {
			transaction.begin();
			EmployeeBean empObj = manager.find(EmployeeBean.class, bean.getId());
			if (bean.getName() != null && bean.getName() != "") {
				empObj.setName(bean.getName());
			}
			if (bean.getPassword() != null && bean.getPassword() != "") {
				empObj.setPassword(bean.getPassword());
			}
			if (bean.getDob() != null) {
				empObj.setDob(bean.getDob());
			}
			isUpdate = true;
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdate;
	}
}
