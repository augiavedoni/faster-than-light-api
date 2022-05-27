package com.codesherpas.ftl.service.impl;

import org.springframework.stereotype.Service;

import com.codesherpas.ftl.dto.SpaceshipDTO;
import com.codesherpas.ftl.service.WeaponService;

@Service
public class WeaponServiceImpl implements WeaponService {
	@Override
	public SpaceshipDTO shootSpaceship(SpaceshipDTO victim) {
		victim.setHealth(victim.getHealth() - 1);
		
		return victim;
	}
}
