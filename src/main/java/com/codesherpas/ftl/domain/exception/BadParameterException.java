package com.codesherpas.ftl.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadParameterException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private Object fieldValue;
	
	public BadParameterException(String fieldName, Object fieldValue) {
		super(String.format("The field %s cannot be %s", fieldName, fieldValue));
		
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}
}
