package com.codesherpas.ftl.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesherpas.ftl.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.exception.BadParameterException;
import com.codesherpas.ftl.model.PowerGenerator;
import com.codesherpas.ftl.repository.PowerGeneratorRepository;
import com.codesherpas.ftl.service.PowerGeneratorService;

@Service
public class PowerGeneratorServiceImpl implements PowerGeneratorService {
	@Autowired
	private ModelMapper modelMapper;
	
	private PowerGeneratorRepository powerGeneratorRepository;
	
	public PowerGeneratorServiceImpl(ModelMapper modelMapper, PowerGeneratorRepository powerGeneratorRepository) {
		super();
		this.powerGeneratorRepository = powerGeneratorRepository;
	}
	
//	private PowerGeneratorDTO convertToDTO(PowerGenerator powerGenerator) {
//		PowerGeneratorDTO powerGeneratorDTO = modelMapper.map(powerGenerator, PowerGeneratorDTO.class);
//		
//		return powerGeneratorDTO;
//	}
	
	private PowerGenerator convertToEntity(PowerGeneratorDTO powerGeneratorDTO) {
		PowerGenerator powerGenerator = modelMapper.map(powerGeneratorDTO, PowerGenerator.class);
		
		return powerGenerator;
	}

	@Override
	public PowerGeneratorDTO savePowerGenerator(PowerGeneratorDTO powerGeneratorDTO) {
		if(powerGeneratorDTO.getTotalPower() == null || powerGeneratorDTO.getTotalPower() < 0) {
			throw new BadParameterException("total-power", powerGeneratorDTO.getTotalPower());
		}
		
		powerGeneratorDTO.setPowerNotInUse(powerGeneratorDTO.getTotalPower());
		
		PowerGenerator savedPowerGenerator = powerGeneratorRepository.save(convertToEntity(powerGeneratorDTO));
		
		powerGeneratorDTO.setId(savedPowerGenerator.getId());
		
		return powerGeneratorDTO;
	}
}
