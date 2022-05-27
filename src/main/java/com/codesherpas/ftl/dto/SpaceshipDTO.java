package com.codesherpas.ftl.dto;

import com.codesherpas.ftl.model.Weapon;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SpaceshipDTO {
	private long id;
	private String name;
	private Integer health;
	private Weapon weapon;
	
	@JsonProperty(value = "power-generator")
	private PowerGeneratorDTO powerGenerator;
	
	public SpaceshipDTO() {}
	
	public SpaceshipDTO(long id, String name, Integer health, Weapon weapon, PowerGeneratorDTO powerGenerator) {
		this.id = id;
		this.name = name;
		this.health = health;
		this.weapon = weapon;
		this.powerGenerator = powerGenerator;
	}
}
