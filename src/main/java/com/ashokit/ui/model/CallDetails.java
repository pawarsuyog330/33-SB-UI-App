package com.ashokit.ui.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CallDetails {
	
	private Integer callId;
	
	private Long fromNumber;
	
	private Long toNumber;
	
	private Integer callDuration;
	
	private LocalDateTime calledOn;

}
