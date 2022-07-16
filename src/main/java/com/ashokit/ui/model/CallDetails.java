package com.ashokit.ui.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CallDetails {
	
	private Integer id;
	
	private Long fromNumber;
	
	private Long toNumber;
	
	private Integer duration;
	
	private LocalDateTime calledOn;

}
