package com.codesherpas.ftl.infrastructure.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesherpas.ftl.domain.data.Spaceship;
import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.domain.exception.ResourceNotFoundException;
import com.codesherpas.ftl.domain.port.persistence.SpaceshipPersistencePort;
import com.codesherpas.ftl.infrastructure.entity.SpaceshipEntity;
import com.codesherpas.ftl.infrastructure.mappers.SpaceshipMapper;
import com.codesherpas.ftl.infrastructure.repository.SpaceshipRepository;

@Service
public class SpaceshipJpaAdapter implements SpaceshipPersistencePort {
	@Autowired
	SpaceshipRepository spaceshipRepository;

	@Override
	public SpaceshipDTO saveSpaceship(SpaceshipDTO spaceshipDTO) {
		SpaceshipEntity spaceship = SpaceshipMapper.INSTANCE.convertToEntity(spaceshipDTO);
		SpaceshipEntity savedSpaceship = spaceshipRepository.save(spaceship);

		return SpaceshipMapper.INSTANCE.convertToDto(savedSpaceship);
	}
	
	@Override
	public SpaceshipDTO saveSpaceship(Spaceship spaceship) {
		SpaceshipEntity spaceshipEntity = SpaceshipMapper.INSTANCE.convertToEntity(spaceship);
		SpaceshipEntity savedSpaceship = spaceshipRepository.save(spaceshipEntity);

		return SpaceshipMapper.INSTANCE.convertToDto(savedSpaceship);
	}

	@Override
	public List<SpaceshipDTO> getSpaceships() {
		List<SpaceshipEntity> spaceships = spaceshipRepository.findAll();

		return SpaceshipMapper.INSTANCE.convertToListOfDto(spaceships);
	}

	@Override
	public Spaceship getSpaceshipById(long spaceshipId) {
		SpaceshipEntity spaceship = spaceshipRepository.findById(spaceshipId)
				.orElseThrow(() -> new ResourceNotFoundException("Spaceship", "id", spaceshipId));

		return SpaceshipMapper.INSTANCE.convertToData(spaceship);
	}
}
