package com.codesherpas.ftl.domain.port.service;

import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.domain.dto.WeaponDTO;

public interface WeaponServicePort {
	boolean isWeaponValid(WeaponDTO weaponDTO);
	WeaponDTO saveWeapon(WeaponDTO weaponDTO);
	public SpaceshipDTO shootSpaceship(SpaceshipDTO victim);
}
