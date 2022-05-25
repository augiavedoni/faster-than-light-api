package com.codesherpas.ftl.resources;

public class SpaceshipResource {
	private long id;
	private String name;
	private Integer health;
	
	public SpaceshipResource(long id, String name, Integer health) {
		this.id = id;
		this.name = name;
		this.health = health;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}
}
