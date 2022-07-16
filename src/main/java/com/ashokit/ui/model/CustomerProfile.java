package com.ashokit.ui.model;

import java.util.List;

import lombok.Data;

@Data
public class CustomerProfile {
	private  Long  phoneNumber;
	private  String username;
	private  String email;
	private  String planId;
	private  Plan  currentPlan;
	private  List<Long> friendsContactNumbers;

}
