package com.codesherpas.ftl.domain.service.impl;

import java.util.List;

import com.codesherpas.ftl.domain.data.Spaceship;
import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.domain.dto.WeaponDTO;
import com.codesherpas.ftl.domain.exception.BadParameterException;
import com.codesherpas.ftl.domain.exception.DestroyedSpaceshipException;
import com.codesherpas.ftl.domain.port.persistence.SpaceshipPersistencePort;
import com.codesherpas.ftl.domain.port.service.PowerGeneratorServicePort;
import com.codesherpas.ftl.domain.port.service.SpaceshipServicePort;


public class SpaceshipServiceImpl implements SpaceshipServicePort {
	private SpaceshipPersistencePort spaceshipPersistencePort;
	private PowerGeneratorServicePort powerGeneratorService;

	public SpaceshipServiceImpl(SpaceshipPersistencePort spaceshipPersistencePort, PowerGeneratorServicePort powerGeneratorService) {
		this.spaceshipPersistencePort = spaceshipPersistencePort;
		this.powerGeneratorService = powerGeneratorService;
	}

	@Override
	public SpaceshipDTO saveSpaceship(SpaceshipDTO spaceshipDTO) {
		if(spaceshipDTO.getName() == null || spaceshipDTO.getName().isBlank()) {
			throw new BadParameterException("name", spaceshipDTO.getName());
		} else if(spaceshipDTO.getHealth() == null || spaceshipDTO.getHealth() <= 0) {
			throw new BadParameterException("health", spaceshipDTO.getHealth());
		} else if(spaceshipDTO.getPowerGenerator() == null) {
			throw new BadParameterException("power-generator", spaceshipDTO.getPowerGenerator());
		}
		
		spaceshipDTO.setWeapon(new WeaponDTO());
		
		PowerGeneratorDTO savedPowerGenerator = powerGeneratorService.savePowerGenerator(spaceshipDTO.getPowerGenerator());
		
		spaceshipDTO.setPowerGenerator(savedPowerGenerator);
		
		return spaceshipPersistencePort.saveSpaceship(spaceshipDTO);
	}

	@Override
	public List<SpaceshipDTO> getSpaceships() {
		return spaceshipPersistencePort.getSpaceships();
	}

	@Override
	public SpaceshipDTO shootSpaceship(long attackerId, long victimId) {
		Spaceship attackerSpaceship = spaceshipPersistencePort.getSpaceshipById(attackerId);
		
		if(attackerSpaceship.isDestroyed()) {
			throw new DestroyedSpaceshipException(attackerSpaceship.getName());
		}
		
		Spaceship victimSpaceship = spaceshipPersistencePort.getSpaceshipById(victimId);
		
		if(victimSpaceship.isDestroyed()) {
			throw new DestroyedSpaceshipException(victimSpaceship.getName());
		}
		
		Spaceship damagedSpaceship = attackerSpaceship.getWeapon().attackSpaceship(victimSpaceship);
		
		return spaceshipPersistencePort.saveSpaceship(damagedSpaceship);
	}
}
