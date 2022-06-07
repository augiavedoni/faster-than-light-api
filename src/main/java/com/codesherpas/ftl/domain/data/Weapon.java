package com.codesherpas.ftl.domain.data;

import lombok.Data;

@Data
public class Weapon {
	private long id;
	private Integer powerNeeded;
	
	public Weapon() {}
	
	public Weapon(long id, Integer powerNeeded) {
		this.id = id;
		this.powerNeeded = powerNeeded;
	}
	
	public boolean canShoot(PowerGenerator powerGenerator) {
		if(powerGenerator.getPowerConsumedByWeapon().equals(this.powerNeeded)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Spaceship attackSpaceship(Spaceship victim) {
		victim.setHealth(victim.getHealth() - 1);
		
		return victim;
	}
}