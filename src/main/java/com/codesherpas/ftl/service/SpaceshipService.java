package com.codesherpas.ftl.service;

import java.util.List;

import com.codesherpas.ftl.model.Spaceship;

public interface SpaceshipService {
	Spaceship saveSpaceship(Spaceship spaceship);
	List<Spaceship> getSpaceships();
	Spaceship shootSpaceship(long attackerId, long victimSpaceshipId);
}
