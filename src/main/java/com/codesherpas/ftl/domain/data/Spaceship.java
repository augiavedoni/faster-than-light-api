package com.codesherpas.ftl.domain.data;

import lombok.Data;

@Data
public class Spaceship {
	private long id;
	private String name;
	private Integer health;
	private Weapon weapon;
	private PowerGenerator powerGenerator;
	
	public Spaceship() {}
	
	public Spaceship(long id, String name, Integer health, Weapon weapon, PowerGenerator powerGenerator) {
		this.id = id;
		this.name = name;
		this.health = health;
		this.weapon = weapon;
		this.powerGenerator = powerGenerator;
	}
	
	public boolean isDestroyed() {
		return this.health.equals(0);
	}
}
