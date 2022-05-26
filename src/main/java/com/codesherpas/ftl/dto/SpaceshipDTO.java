package com.codesherpas.ftl.dto;

import lombok.Data;

@Data
public class SpaceshipDTO {
	private long id;
	private String name;
	private Integer health;
	
	public SpaceshipDTO() {}
	
	public SpaceshipDTO(long id, String name, Integer health) {
		this.id = id;
		this.name = name;
		this.health = health;
	}
}
