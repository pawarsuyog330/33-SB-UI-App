package com.ashokit.ui.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Customer {
	@NotNull
	private Long phoneNumber;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String planId;
	
	private List<Plan> plansList;
	

}
