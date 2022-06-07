package com.codesherpas.ftl.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeaponDTO {
	private long id;
	
	@JsonProperty(value = "weapon-power-needed")
	private Integer powerNeeded;
	
	public WeaponDTO() {}
	
	public WeaponDTO(long id, Integer powerNeeded) {
		this.id = id;
		this.powerNeeded = powerNeeded;
	}
}
