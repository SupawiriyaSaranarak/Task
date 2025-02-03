package com.example.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.dto.ResponseMessage;
import com.example.task.dto.TaskRequest;
import com.example.task.dto.UpdateTaskRequest;
import com.example.task.entity.Task;
import com.example.task.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@GetMapping("/all")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }
	@GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }
	@PostMapping
    public ResponseMessage createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }
	@PutMapping
    public Task updateTaskById(@RequestBody UpdateTaskRequest updateTaskRequest) {
        return taskService.updateTaskById(updateTaskRequest);
    }

}
