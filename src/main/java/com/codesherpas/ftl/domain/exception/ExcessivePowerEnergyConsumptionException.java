package com.codesherpas.ftl.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class ExcessivePowerEnergyConsumptionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private Integer actualPowerEnergyConsumption;
	private Integer maximumPowerConsumptionAllowed;
	
	
	public ExcessivePowerEnergyConsumptionException(String resourceName, Integer actualPowerEnergyConsumption, Integer maximumPowerConsumptionAllowed) {
		super(String.format("%s can't operate because the maximum power energy consumption allowed for it is %s and the actual consumption is %s", resourceName, maximumPowerConsumptionAllowed, actualPowerEnergyConsumption));
		this.resourceName = resourceName;
		this.actualPowerEnergyConsumption = actualPowerEnergyConsumption;
		this.maximumPowerConsumptionAllowed = maximumPowerConsumptionAllowed;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getResourceName() {
		return resourceName;
	}


	public Integer getActualPowerEnergyConsumption() {
		return actualPowerEnergyConsumption;
	}


	public Integer getMaximumPowerConsumptionAllowed() {
		return maximumPowerConsumptionAllowed;
	}
}
