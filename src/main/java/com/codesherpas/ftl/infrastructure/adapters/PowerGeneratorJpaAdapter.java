package com.codesherpas.ftl.infrastructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesherpas.ftl.domain.data.PowerGenerator;
import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.exception.ResourceNotFoundException;
import com.codesherpas.ftl.domain.port.persistence.PowerGeneratorPersistencePort;
import com.codesherpas.ftl.infrastructure.entity.PowerGeneratorEntity;
import com.codesherpas.ftl.infrastructure.mappers.PowerGeneratorMapper;
import com.codesherpas.ftl.infrastructure.repository.PowerGeneratorRepository;

@Service
public class PowerGeneratorJpaAdapter implements PowerGeneratorPersistencePort{
	@Autowired
	PowerGeneratorRepository powerGeneratorRepository;
	
	@Override
	public PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGenerator) {
		PowerGeneratorEntity powerGeneratorEntity = PowerGeneratorMapper.INSTANCE.convertToEntity(powerGenerator);
		PowerGeneratorEntity savedPowerGenerator = powerGeneratorRepository.save(powerGeneratorEntity);
		
		return PowerGeneratorMapper.INSTANCE.convertToDto(savedPowerGenerator);
	}
	
	@Override
	public PowerGeneratorDTO savePowerGenerator(PowerGenerator powerGenerator) {
		PowerGeneratorEntity powerGeneratorEntity = PowerGeneratorMapper.INSTANCE.convertToEntity(powerGenerator);
		PowerGeneratorEntity savedPowerGenerator = powerGeneratorRepository.save(powerGeneratorEntity);
		
		return PowerGeneratorMapper.INSTANCE.convertToDto(savedPowerGenerator);
	}

	@Override
	public PowerGenerator getPowerGeneratorById(long powerGeneratorId) {
		PowerGeneratorEntity powerGeneratorEntity = powerGeneratorRepository.findById(powerGeneratorId)
				.orElseThrow(() -> new ResourceNotFoundException("PowerGenerator", "id", powerGeneratorId));
		
		return PowerGeneratorMapper.INSTANCE.convertToData(powerGeneratorEntity);
	}
}
