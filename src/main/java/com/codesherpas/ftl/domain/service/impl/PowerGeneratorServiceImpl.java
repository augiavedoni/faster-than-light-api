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
	public PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGeneratorDTO) {
		if(powerGeneratorDTO.getTotalPower() == null || powerGeneratorDTO.getTotalPower() < 0) {
			throw new BadParameterException("total-power", powerGeneratorDTO.getTotalPower());
		}
		
		powerGeneratorDTO.setPowerNotInUse(powerGeneratorDTO.getTotalPower());
		
		return powerGeneratorPersistencePort.savePowerGenerator(powerGeneratorDTO);
	}
}
