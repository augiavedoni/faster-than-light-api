package com.codesherpas.ftl.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SpaceshipDTO {
	private long id;
	private String name;
	private Integer health;
	private WeaponDTO weapon;
	
	@JsonProperty(value = "power-generator")
	private PowerGeneratorDTO powerGenerator;
	
	public SpaceshipDTO() {}
	
	public SpaceshipDTO(long id, String name, Integer health, WeaponDTO weapon, PowerGeneratorDTO powerGenerator) {
		this.id = id;
		this.name = name;
		this.health = health;
		this.weapon = weapon;
		this.powerGenerator = powerGenerator;
	}
}
