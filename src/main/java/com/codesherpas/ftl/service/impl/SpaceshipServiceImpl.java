package com.codesherpas.ftl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codesherpas.ftl.dto.SpaceshipDTO;
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
	public SpaceshipDTO saveSpaceship(SpaceshipDTO spaceshipDTO) {
		if(spaceshipDTO.getName() == null || spaceshipDTO.getName().isBlank()) {
			throw new BadParameterException("name", spaceshipDTO.getName());
		} else if(spaceshipDTO.getHealth() == null || spaceshipDTO.getHealth() <= 0) {
			throw new BadParameterException("health", spaceshipDTO.getHealth());
		}
		
		Spaceship spaceship = new Spaceship(spaceshipDTO.getName(), spaceshipDTO.getHealth());
		
		Spaceship savedSpaceship = spaceshipRepository.save(spaceship);
		
		spaceshipDTO.setId(savedSpaceship.getId());
		
		return spaceshipDTO;
	}

	@Override
	public List<SpaceshipDTO> getSpaceships() {
		List<Spaceship> spaceships = spaceshipRepository.findAll();
		List<SpaceshipDTO> spaceshipsDTO = new ArrayList<SpaceshipDTO>();
		
		for(Spaceship spaceship: spaceships) {
			SpaceshipDTO spaceshipDTO = new SpaceshipDTO(spaceship.getId(), spaceship.getName(), spaceship.getHealth());
			
			spaceshipsDTO.add(spaceshipDTO);
		}
		
		return spaceshipsDTO;
	}

	@Override
	public SpaceshipDTO shootSpaceship(long attackerId, long victimId) {
		Spaceship attackerSpaceship = spaceshipRepository.findById(attackerId)
				.orElseThrow(() -> new ResourceNotFoundException("Spaceship", "id", attackerId));
		
		if(attackerSpaceship.getHealth().equals(0)) {
			throw new DestroyedSpaceshipException(attackerSpaceship.getName());
		}
		
		Spaceship victimSpaceship = spaceshipRepository.findById(victimId)
				.orElseThrow(() -> new ResourceNotFoundException("Spaceship", "id", victimId));
		
		if(victimSpaceship.getHealth().equals(0)) {
			throw new DestroyedSpaceshipException(victimSpaceship.getName());
		}
		
		int spaceshipHealth = victimSpaceship.getHealth() - 1;
		
		victimSpaceship.setHealth(spaceshipHealth);
		
		spaceshipRepository.save(victimSpaceship);
		
		SpaceshipDTO spaceshipDTO = new SpaceshipDTO(victimSpaceship.getId(), victimSpaceship.getName(), victimSpaceship.getHealth());
		
		return spaceshipDTO;
	}
}
