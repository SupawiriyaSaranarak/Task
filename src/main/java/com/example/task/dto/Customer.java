package com.example.task.dto;

import java.time.LocalDate;

import com.example.task.enums.StatusCodeEnum;

public class Customer {  

	private int customerId;
		
    private String firstname;
	    
    private String lastname;
    
    private String email;
    
    private LocalDate customerDate;
        
    private boolean isVip = false;
    
    private StatusCodeEnum statusCode = StatusCodeEnum.ACTIVE;
    
    public Customer(int customerId, String firstname, String lastname, String email, LocalDate customerDate,
			boolean isVip, StatusCodeEnum statusCode) {
		super();
		this.customerId = customerId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.customerDate = customerDate;
		this.isVip = isVip;
		this.statusCode = statusCode;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCustomerDate() {
		return customerDate;
	}

	public void setCustomerDate(LocalDate customerDate) {
		this.customerDate = customerDate;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}

	public StatusCodeEnum getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCodeEnum statusCode) {
		this.statusCode = statusCode;
	}
    

}
