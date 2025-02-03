package com.example.task.service;




import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import com.example.task.dto.Customer;
import com.example.task.dto.ResponseMessage;
import com.example.task.dto.TaskRequest;
import com.example.task.dto.UpdateTaskRequest;
import com.example.task.dto.Officer;
import com.example.task.entity.Task;
import com.example.task.enums.StatusCodeEnum;
import com.example.task.enums.TaskStatusEnum;
import com.example.task.enums.TaskTypeEnum;
import com.example.task.repository.TaskRepository;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	
	@InjectMocks
	private TaskService targetService;
	@Mock
	private TaskRepository taskRepository;
	@Mock	
	private CustomerService customerService;
	@Mock
	private BackOfficeService backOfficeService;
	
	@Test
    void testCreateTask_Success() {
		Customer customer = new Customer(1, "firstname", "lastname", "mockcustomer@mail.com",LocalDate.now(),false,StatusCodeEnum.INACTIVE);
		
        when(customerService.getUserByEmail("mockcustomer@mail.com")).thenReturn(customer);
        Task newTask  = new Task();
                
        when(taskRepository.save(any(Task.class))).thenReturn(newTask);
        
        ResponseMessage responseMessage = new ResponseMessage("Success");
        
        when(backOfficeService.sendTask(any(Task.class))).thenReturn(responseMessage);
        
        when(backOfficeService.getOfficerByTaskType(TaskTypeEnum.CARD_ACTIVATION)).thenReturn(new Officer("officerName"));
        
        TaskRequest request = new TaskRequest("mockcustomer@mail.com", TaskTypeEnum.CARD_ACTIVATION, "description");
        
        ResponseMessage result = targetService.createTask(request);
        
     // Assert
        assertNotNull(result);
        assertEquals("Success", result.getMessage());

	}
	
	@Test
    void updateTaskById_Success() {
		UpdateTaskRequest request = new UpdateTaskRequest(1, "officerName", TaskStatusEnum.COMPLETED);
		Task task  = new Task();
		task.setTaskId(1);
		task.setOfficer("officerName");
		task.setTaskStatus(TaskStatusEnum.COMPLETED);
		when(taskRepository.findById(1)).thenReturn(Optional.of(task));
		
                
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        
 
     
        Task result = targetService.updateTaskById(request);
        
     // Assert
        assertNotNull(result);
        assertEquals(1, result.getTaskId());
        assertEquals("officerName", result.getOfficer());
        assertEquals(TaskStatusEnum.COMPLETED, result.getTaskStatus());

	}
	

	 

}
