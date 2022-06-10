package com.codesherpas.ftl.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.codesherpas.ftl.domain.data.Spaceship;
import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.infrastructure.entity.SpaceshipEntity;

@Mapper
public interface SpaceshipMapper {
	SpaceshipMapper INSTANCE = Mappers.getMapper(SpaceshipMapper.class);
	
	SpaceshipDTO convertToDto(SpaceshipEntity spaceship);
	Spaceship convertToData(SpaceshipEntity spaceship);
	SpaceshipEntity convertToEntity(SpaceshipDTO spaceship);
	SpaceshipEntity convertToEntity(Spaceship spaceship);
	List<SpaceshipDTO> convertToListOfDto(List<SpaceshipEntity> spaceships);
}
