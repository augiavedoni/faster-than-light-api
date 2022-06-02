package com.codesherpas.ftl.domain.port.persistence;

import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;

public interface PowerGeneratorPersistencePort {
	PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGeneratorDTO);
}
