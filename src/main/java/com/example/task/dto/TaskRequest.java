package com.example.task.dto;

import com.example.task.enums.TaskTypeEnum;

public class TaskRequest {
	public TaskRequest(String customerEmail, TaskTypeEnum taskType, String taskDescription) {
		super();
		this.customerEmail = customerEmail;
		this.taskType = taskType;
		this.taskDescription = taskDescription;
	}
	String customerEmail;
	TaskTypeEnum taskType;
	String taskDescription;
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public TaskTypeEnum getTaskType() {
		return taskType;
	}
	public void setTaskType(TaskTypeEnum taskType) {
		this.taskType = taskType;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	
}
