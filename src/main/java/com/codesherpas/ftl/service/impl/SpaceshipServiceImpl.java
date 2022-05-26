package com.codesherpas.ftl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codesherpas.ftl.exception.BadParameterException;
import com.codesherpas.ftl.exception.DestroyedSpaceshipException;
import com.codesherpas.ftl.exception.ResourceNotFoundException;
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
		if(spaceship.getName() == null || spaceship.getName().isBlank()) {
			throw new BadParameterException("name", spaceship.getName());
		} else if(spaceship.getHealth() == null || spaceship.getHealth() >= 0) {
			throw new BadParameterException("health", spaceship.getHealth());
		}
		
		return spaceshipRepository.save(spaceship);
	}

	@Override
	public List<Spaceship> getSpaceships() {
		return spaceshipRepository.findAll();
	}

	@Override
	public Spaceship shootSpaceship(long attackerId, long victimId) {
		Spaceship attackerSpaceship = spaceshipRepository.findById(attackerId)
				.orElseThrow(() -> new ResourceNotFoundException("Spaceship", "id", attackerId));
		
		if(attackerSpaceship.isDestroyed()) {
			throw new DestroyedSpaceshipException(attackerSpaceship.getName());
		}
		
		Spaceship victimSpaceship = spaceshipRepository.findById(victimId)
				.orElseThrow(() -> new ResourceNotFoundException("Spaceship", "id", victimId));
		
		if(victimSpaceship.isDestroyed()) {
			throw new DestroyedSpaceshipException(victimSpaceship.getName());
		}
		
		int spaceshipHealth = victimSpaceship.getHealth() - 1;
		
		victimSpaceship.setHealth(spaceshipHealth);
		
		spaceshipRepository.save(victimSpaceship);
		
		return victimSpaceship;
	}
}
