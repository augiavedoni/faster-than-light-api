package com.codesherpas.ftl.domain.port.service;

import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;

public interface PowerGeneratorServicePort {
	public boolean isPowerGeneratorValid(PowerGeneratorDTO powerGenerator);
	PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGenerator);
	PowerGeneratorDTO editPowerConsumedByWeapon(long powerGeneratorId, long weaponId, int newPowerConsumedByWeapon);
}
