package com.codesherpas.ftl.domain.port.persistence;

import java.util.List;

import com.codesherpas.ftl.domain.data.Spaceship;
import com.codesherpas.ftl.domain.dto.SpaceshipDTO;

public interface SpaceshipPersistencePort {
	SpaceshipDTO saveSpaceship(SpaceshipDTO spaceshipDTO);
	SpaceshipDTO saveSpaceship(Spaceship spaceship);
	List<SpaceshipDTO> getSpaceships();
	Spaceship getSpaceshipById(long spaceshipId);
}
