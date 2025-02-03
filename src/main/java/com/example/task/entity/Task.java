package com.example.task.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.task.enums.TaskStatusEnum;
import com.example.task.enums.TaskTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;
	@Column(nullable = false)
	private String customerEmail;
	@Enumerated(EnumType.STRING) // Stores the enum as a string in the database
    @Column(nullable = false)
	private TaskTypeEnum taskType;
	@Column(nullable = true)
	private String taskDescription;
	@Enumerated(EnumType.STRING) // Stores the enum as a string in the database
    @Column(nullable = false)
	private TaskStatusEnum taskStatus = TaskStatusEnum.IN_PROGRESS;
	@Column(nullable = false)
	private String officer;
	@CreationTimestamp
    @Column(updatable = false)
	private LocalDateTime createdOn;
	@CreationTimestamp
    @Column(updatable = true)
	private LocalDateTime modifiedOn;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
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
	public TaskStatusEnum getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatusEnum taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String officer) {
		this.officer = officer;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
}
