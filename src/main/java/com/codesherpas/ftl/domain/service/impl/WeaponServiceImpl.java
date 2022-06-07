package com.codesherpas.ftl.domain.service.impl;

import org.springframework.stereotype.Service;

import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.domain.dto.WeaponDTO;
import com.codesherpas.ftl.domain.exception.BadParameterException;
import com.codesherpas.ftl.domain.port.persistence.WeaponPersistencePort;
import com.codesherpas.ftl.domain.port.service.WeaponServicePort;

@Service
public class WeaponServiceImpl implements WeaponServicePort {
	private WeaponPersistencePort weaponPersistencePort;
	
	public WeaponServiceImpl(WeaponPersistencePort weaponPersistencePort) {
		this.weaponPersistencePort = weaponPersistencePort;
	}
	
	@Override
	public boolean isWeaponValid(WeaponDTO weaponDTO) {
		if(weaponDTO == null) {
			throw new BadParameterException("weapon", weaponDTO);
		} else if(weaponDTO.getPowerNeeded() == null || weaponDTO.getPowerNeeded() <= 0) {
			throw new BadParameterException("weapon-power-needed", weaponDTO.getPowerNeeded());
		}
		
		return true;
	}
	
	@Override
	public WeaponDTO saveWeapon(WeaponDTO weaponDTO) {
		return weaponPersistencePort.saveWeapon(weaponDTO);
	}

	@Override
	public SpaceshipDTO shootSpaceship(SpaceshipDTO victim) {
		victim.setHealth(victim.getHealth() - 1);
		
		return victim;
	}
}
