package com.example.task.service;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.task.dto.Customer;

@Service
public class CustomerService {

	@Autowired
    private RestTemplate restTemplate;
	@Value("${app.customer.base-url}${app.customer.email-endpoint}")
	private String customerEmailEndpoint;
	

    public Customer getUserByEmail(String email) {
        
        
        ResponseEntity<Customer> response;
        try {
            response = restTemplate.exchange(customerEmailEndpoint, HttpMethod.GET, null, Customer.class, Map.of("email", email));
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
