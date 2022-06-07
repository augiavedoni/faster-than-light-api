package com.codesherpas.ftl.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PowerGeneratorDTO {
	private long id;
	
	@JsonProperty(value = "total-power")
	private Integer totalPower;
	
	@JsonProperty(value = "power-not-in-use")
	private Integer powerNotInUse;
	
	@JsonProperty(value = "power-consumed-by-weapon")
	private Integer powerConsumedByWeapon;
	
	PowerGeneratorDTO() {}
	
	public PowerGeneratorDTO(long id, Integer totalPower, Integer powerNotInUse, Integer powerConsumedByWeapon) {
		super();
		this.id = id;
		this.totalPower = totalPower;
		this.powerNotInUse = powerNotInUse;
		this.powerConsumedByWeapon = powerConsumedByWeapon;
	}
}
