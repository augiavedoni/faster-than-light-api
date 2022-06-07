package com.codesherpas.ftl.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class MissingPowerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private Integer actualPower;
	private Integer requiredPower;
	
	public MissingPowerException(String resourceName, Integer requiredPower, Integer actualPower) {
		super(String.format("%s can't operate because the amount of energy it requires is %s and actually has %s", resourceName, requiredPower, actualPower));
		this.resourceName = resourceName;
		this.actualPower = actualPower;
		this.requiredPower = requiredPower;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResourceName() {
		return resourceName;
	}

	public Integer getActualPower() {
		return actualPower;
	}

	public Integer getRequiredPower() {
		return requiredPower;
	}
}
