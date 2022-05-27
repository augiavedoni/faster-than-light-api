package com.codesherpas.ftl.dto;

import com.codesherpas.ftl.model.Weapon;

import lombok.Data;

@Data
public class SpaceshipDTO {
	private long id;
	private String name;
	private Integer health;
	private Weapon weapon;
	
	public SpaceshipDTO() {}
	
	public SpaceshipDTO(long id, String name, Integer health, Weapon weapon) {
		this.id = id;
		this.name = name;
		this.health = health;
		this.weapon = weapon;
	}
}
