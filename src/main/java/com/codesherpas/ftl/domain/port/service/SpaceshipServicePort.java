package com.codesherpas.ftl.domain.port.service;

import java.util.List;

import com.codesherpas.ftl.domain.dto.SpaceshipDTO;

public interface SpaceshipServicePort {
	SpaceshipDTO saveSpaceship(SpaceshipDTO spaceshipDTO);
	List<SpaceshipDTO> getSpaceships();
	SpaceshipDTO shootSpaceship(long attackerId, long victimSpaceshipId);
}
