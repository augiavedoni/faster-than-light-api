package com.codesherpas.ftl.domain.port.service;

import com.codesherpas.ftl.domain.dto.SpaceshipDTO;

public interface WeaponServicePort {
	public SpaceshipDTO shootSpaceship(SpaceshipDTO victim);
}
