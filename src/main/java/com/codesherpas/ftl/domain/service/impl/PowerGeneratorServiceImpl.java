package com.codesherpas.ftl.domain.service.impl;

import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.exception.BadParameterException;
import com.codesherpas.ftl.domain.port.persistence.PowerGeneratorPersistencePort;
import com.codesherpas.ftl.domain.port.service.PowerGeneratorServicePort;

public class PowerGeneratorServiceImpl implements PowerGeneratorServicePort {
	private PowerGeneratorPersistencePort powerGeneratorPersistencePort;
	
	public PowerGeneratorServiceImpl(PowerGeneratorPersistencePort powerGeneratorPersistencePort) {
		this.powerGeneratorPersistencePort = powerGeneratorPersistencePort;
	}
	
	@Override
	public boolean isPowerGeneratorValid(PowerGeneratorDTO powerGeneratorDTO) {
		if(powerGeneratorDTO.getTotalPower() == null || powerGeneratorDTO.getTotalPower() < 0) {
			throw new BadParameterException("total-power", powerGeneratorDTO.getTotalPower());
		} else if(powerGeneratorDTO.getPowerConsumedByWeapon() == null || powerGeneratorDTO.getPowerConsumedByWeapon() < 0) {
			throw new BadParameterException("power-consumed-by-weapon", powerGeneratorDTO.getTotalPower());
		}
		
		return true;
	}

	@Override
	public PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGeneratorDTO) {
		powerGeneratorDTO.setPowerNotInUse(powerGeneratorDTO.getTotalPower() - powerGeneratorDTO.getPowerConsumedByWeapon());
		
		return powerGeneratorPersistencePort.savePowerGenerator(powerGeneratorDTO);
	}
}
