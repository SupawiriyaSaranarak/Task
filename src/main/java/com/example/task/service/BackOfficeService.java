package com.example.task.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.task.dto.Officer;
import com.example.task.dto.ResponseMessage;
import com.example.task.entity.Task;
import com.example.task.enums.TaskTypeEnum;




@Service
public class BackOfficeService {

	@Autowired
    private RestTemplate restTemplate;

    public Officer getOfficerByTaskType (TaskTypeEnum taskType) {
        String url = "http://localhost:8082/back-office/task/"+taskType+"/officer";
        
        ResponseEntity<Officer> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, null, Officer.class);
        } catch (HttpStatusCodeException ex) {
        	response = null;
        	if (ex.getStatusCode() != null && Objects.equals(ex.getStatusCode(), HttpStatusCode.valueOf(404))) {
        		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        	}
        }

        if (Objects.nonNull(response) && Objects.nonNull(response.getBody())) {
        	return response.getBody();
        } else {
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }    
    }
    
    @PostMapping("/task")
	public ResponseMessage sendTask (@RequestBody Task task) {	
    	String url = "http://localhost:8082/back-office/task";  

        // Create request entity
        HttpEntity<Task> request = new HttpEntity<>(task);
        
        ResponseEntity<ResponseMessage> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, request, ResponseMessage.class);
        } catch (HttpStatusCodeException ex) {
        	response = null;
        	if (ex.getStatusCode() != null && Objects.equals(ex.getStatusCode(), HttpStatusCode.valueOf(404))) {
        		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        	}
        }

        if (Objects.nonNull(response) && Objects.nonNull(response.getBody())) {
        	return response.getBody();
        } else {
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
	}
}
