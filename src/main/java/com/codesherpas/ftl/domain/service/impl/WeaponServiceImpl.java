package com.codesherpas.ftl.domain.service.impl;

import org.springframework.stereotype.Service;

import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.domain.port.service.WeaponServicePort;

@Service
public class WeaponServiceImpl implements WeaponServicePort {
	@Override
	public SpaceshipDTO shootSpaceship(SpaceshipDTO victim) {
		victim.setHealth(victim.getHealth() - 1);
		
		return victim;
	}
}
