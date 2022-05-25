package com.codesherpas.ftl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codesherpas.ftl.model.Spaceship;
import com.codesherpas.ftl.repository.SpaceshipRepository;
import com.codesherpas.ftl.service.SpaceshipService;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {
	private SpaceshipRepository spaceshipRepository;
	
	public SpaceshipServiceImpl(SpaceshipRepository spaceshipRepository) {
		super();
		this.spaceshipRepository = spaceshipRepository;
	}

	@Override
	public Spaceship saveSpaceship(Spaceship spaceship) {
		return spaceshipRepository.save(spaceship);
	}

	@Override
	public List<Spaceship> getSpaceships() {
		return spaceshipRepository.findAll();
	}
}
