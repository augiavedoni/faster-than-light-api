package com.codesherpas.ftl.service;

import java.util.List;

import com.codesherpas.ftl.dto.SpaceshipDTO;

public interface SpaceshipService {
	SpaceshipDTO saveSpaceship(SpaceshipDTO spaceship);
	List<SpaceshipDTO> getSpaceships();
	SpaceshipDTO shootSpaceship(long attackerId, long victimSpaceshipId);
}
