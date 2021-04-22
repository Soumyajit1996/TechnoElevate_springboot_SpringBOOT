package com.te.spring_boot.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;


@Data
@JsonRootName("employee_response")
@JsonPropertyOrder({"status_code","message"})
@JsonInclude(JsonInclude.Include.NON_NULL)
//UseOf @JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse implements Serializable{
private int status_code;
	
	private String message;
	
	private String description;
	
	@JsonProperty("Employee_info")
	private EmployeeBean bean;
	
	@JsonProperty("Employee_Details")
	private List<EmployeeBean> beanList;
}
