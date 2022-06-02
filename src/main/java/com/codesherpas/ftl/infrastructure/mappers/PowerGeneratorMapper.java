package com.codesherpas.ftl.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.infrastructure.entity.PowerGeneratorEntity;

@Mapper
public interface PowerGeneratorMapper {
	PowerGeneratorMapper INSTANCE = Mappers.getMapper(PowerGeneratorMapper.class);
	
	PowerGeneratorDTO convertToDto(PowerGeneratorEntity powerGenerator);
	PowerGeneratorEntity convertToEntity(PowerGeneratorDTO powerGeneratorDTO);
}
