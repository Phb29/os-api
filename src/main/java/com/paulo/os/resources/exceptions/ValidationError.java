package com.paulo.os.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	
	private static final long serialVersionUID = 1L;
	
	private List<fieldMessage> erros= new ArrayList<>();

	public ValidationError() {
		super();
		
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
		
	}

	public List<fieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName,String message) {
		this.erros.add(new fieldMessage( fieldName,message));
	}
	

}
