package com.codesherpas.ftl.domain.port.persistence;

import com.codesherpas.ftl.domain.data.PowerGenerator;
import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;

public interface PowerGeneratorPersistencePort {
	PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGenerator);
	PowerGeneratorDTO savePowerGenerator(PowerGenerator powerGenerator);
	PowerGenerator getPowerGeneratorById(long powerGeneratorId);
}
