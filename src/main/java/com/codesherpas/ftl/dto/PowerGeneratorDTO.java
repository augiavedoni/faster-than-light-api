package com.codesherpas.ftl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PowerGeneratorDTO {
	private long id;
	
	@JsonProperty(value = "total-power")
	private Integer totalPower;
	
	@JsonProperty(value = "power-not-in-use")
	private Integer powerNotInUse;
	
	PowerGeneratorDTO() {}
	
	public PowerGeneratorDTO(long id, Integer totalPower, Integer powerNotInUse) {
		super();
		this.id = id;
		this.totalPower = totalPower;
		this.powerNotInUse = powerNotInUse;
	}
}
