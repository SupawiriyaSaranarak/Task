package com.example.task.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.task.dto.Customer;
import com.example.task.dto.Officer;
import com.example.task.dto.ResponseMessage;
import com.example.task.dto.TaskRequest;
import com.example.task.dto.UpdateTaskRequest;
import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;

import io.micrometer.common.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BackOfficeService backOfficeService;
	
	@Transactional
	public ResponseMessage createTask(TaskRequest taskRequest) {
		
		Customer customer = customerService.getUserByEmail(taskRequest.getCustomerEmail());
		Task newTask = new Task();
		
		//Customer who need to create task must be the existing customer if not throw 404
		if (Objects.nonNull(customer) && Objects.nonNull(customer.getEmail())  ) {
			newTask.setCustomerEmail(customer.getEmail());
		} else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		}
		newTask.setTaskType(taskRequest.getTaskType());
		newTask.setTaskDescription(taskRequest.getTaskDescription());
		
		//Get officer name who will have responsibility on specific task by calling external service (Back Office Service)
		Officer officer = backOfficeService.getOfficerByTaskType(taskRequest.getTaskType());
		if (Objects.nonNull(officer) && Objects.nonNull(officer.getName())  ) {
			newTask.setOfficer(officer.getName());
		} else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No response from Back Office not found");
		}
		Task savedTask = taskRepository.save(newTask);
		
		//send task to Back Office and it will Send accepted message back to service
		return backOfficeService.sendTask(savedTask);
	}
	
	public Task updateTaskById(UpdateTaskRequest updateTaskRequest) {
		
		Task task = taskRepository.findById(updateTaskRequest.getTaskId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
		
		
		if (Objects.isNull(task) || 
				StringUtils.isBlank(task.getOfficer()) || 
				!Objects.equals(task.getOfficer(), updateTaskRequest.getOfficer())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot update task under other officer "+task.getOfficer());
		}
		
		task.setTaskStatus(updateTaskRequest.getTaskStatus());
		task.setModifiedOn(LocalDateTime.now());
		return taskRepository.save(task);
	}
	
	public Task getTaskById(int id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
	}
	
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

}
