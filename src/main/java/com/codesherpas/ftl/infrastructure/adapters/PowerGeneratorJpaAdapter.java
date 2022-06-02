package com.codesherpas.ftl.infrastructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.port.persistence.PowerGeneratorPersistencePort;
import com.codesherpas.ftl.infrastructure.entity.PowerGeneratorEntity;
import com.codesherpas.ftl.infrastructure.mappers.PowerGeneratorMapper;
import com.codesherpas.ftl.infrastructure.repository.PowerGeneratorRepository;

@Service
public class PowerGeneratorJpaAdapter implements PowerGeneratorPersistencePort{
	@Autowired
	PowerGeneratorRepository powerGeneratorRepository;
	
	@Override
	public PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGeneratorDTO) {
		PowerGeneratorEntity powerGenerator = PowerGeneratorMapper.INSTANCE.convertToEntity(powerGeneratorDTO);
		PowerGeneratorEntity savedPowerGenerator = powerGeneratorRepository.save(powerGenerator);
		
		return PowerGeneratorMapper.INSTANCE.convertToDto(savedPowerGenerator);
	}
}
