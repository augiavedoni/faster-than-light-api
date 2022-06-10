package com.codesherpas.ftl.domain.service.impl;

import com.codesherpas.ftl.domain.data.PowerGenerator;
import com.codesherpas.ftl.domain.data.Weapon;
import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.exception.BadParameterException;
import com.codesherpas.ftl.domain.exception.ExcessivePowerEnergyConsumptionException;
import com.codesherpas.ftl.domain.port.persistence.PowerGeneratorPersistencePort;
import com.codesherpas.ftl.domain.port.persistence.WeaponPersistencePort;
import com.codesherpas.ftl.domain.port.service.PowerGeneratorServicePort;

public class PowerGeneratorServiceImpl implements PowerGeneratorServicePort {
	private PowerGeneratorPersistencePort powerGeneratorPersistencePort;
	private WeaponPersistencePort weaponPersistencePort;
	
	public PowerGeneratorServiceImpl(PowerGeneratorPersistencePort powerGeneratorPersistencePort, WeaponPersistencePort weaponPersistencePort) {
		this.powerGeneratorPersistencePort = powerGeneratorPersistencePort;
		this.weaponPersistencePort = weaponPersistencePort;
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

	@Override
	public PowerGeneratorDTO editPowerConsumedByWeapon(long powerGeneratorId, long weaponId, int newPowerConsumedByWeapon) {
		PowerGenerator powerGenerator = powerGeneratorPersistencePort.getPowerGeneratorById(powerGeneratorId);
		Weapon weapon = weaponPersistencePort.getWeaponById(weaponId);
		
		powerGenerator.setPowerNotInUse(powerGenerator.getTotalPower() - newPowerConsumedByWeapon);
		
		if(newPowerConsumedByWeapon + powerGenerator.getPowerNotInUse() != powerGenerator.getTotalPower() || newPowerConsumedByWeapon < 0) {
			throw new BadParameterException("newPowerConsumedByWeapon", newPowerConsumedByWeapon);
		}
		
		if(weapon.getPowerNeeded() < newPowerConsumedByWeapon) {
			throw new ExcessivePowerEnergyConsumptionException("weapon", newPowerConsumedByWeapon, weapon.getPowerNeeded());
		}
		
		powerGenerator.setPowerConsumedByWeapon(newPowerConsumedByWeapon);
		
		return powerGeneratorPersistencePort.savePowerGenerator(powerGenerator);
	}
}
