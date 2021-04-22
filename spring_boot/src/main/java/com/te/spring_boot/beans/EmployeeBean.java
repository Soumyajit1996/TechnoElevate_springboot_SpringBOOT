package com.te.spring_boot.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@Table(name="employeeinfo")
@JsonRootName("employee_info")
@JsonPropertyOrder({"id","name"})
@XmlRootElement(name="employee-info")
public class EmployeeBean implements Serializable{
		@Id
		@JsonProperty("emp_id")
		@Column(name="emp_id")
		private Integer id;
		
		@Column(name="emp_name")
		private String name;
		
		@Column
		private Date dob;

		//@JsonIgnore
		//@XmlTransient
		@Column
		private String password;	
}

