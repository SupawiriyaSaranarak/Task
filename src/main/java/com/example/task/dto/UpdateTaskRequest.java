package com.example.task.dto;

import com.example.task.enums.TaskStatusEnum;

public class UpdateTaskRequest {
	public UpdateTaskRequest(int taskId, String officer, TaskStatusEnum taskStatus) {
		super();
		this.taskId = taskId;
		this.officer = officer;
		this.taskStatus = taskStatus;
	}
	private int taskId;
	private String officer;
	private TaskStatusEnum taskStatus;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String officer) {
		this.officer = officer;
	}
	public TaskStatusEnum getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatusEnum taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
}
