package com.codesherpas.ftl.domain.data;

import lombok.Data;

@Data
public class Weapon {
	private long id;
	
	public Weapon() {}
	
	public Weapon(long id) {
		this.id = id;
	}
	
	public Spaceship attackSpaceship(Spaceship victim) {
		victim.setHealth(victim.getHealth() - 1);
		
		return victim;
	}
}