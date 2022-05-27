package com.codesherpas.ftl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ModelMapper modelMapper;
	
	private SpaceshipRepository spaceshipRepository;

	public SpaceshipServiceImpl(SpaceshipRepository spaceshipRepository) {
		super();
		this.spaceshipRepository = spaceshipRepository;
	}
	
	private SpaceshipDTO convertToDTO(Spaceship spaceship) {
		SpaceshipDTO spaceshipDTO = modelMapper.map(spaceship, SpaceshipDTO.class);
		
		return spaceshipDTO;
	}
	
	private Spaceship convertToEntity(SpaceshipDTO spaceshipDTO) {
		Spaceship spaceship = modelMapper.map(spaceshipDTO, Spaceship.class);
		
		return spaceship;
	}

	@Override
	public SpaceshipDTO saveSpaceship(SpaceshipDTO spaceshipDTO) {
		if(spaceshipDTO.getName() == null || spaceshipDTO.getName().isBlank()) {
			throw new BadParameterException("name", spaceshipDTO.getName());
		} else if(spaceshipDTO.getHealth() == null || spaceshipDTO.getHealth() <= 0) {
			throw new BadParameterException("health", spaceshipDTO.getHealth());
		}
		
		Spaceship spaceship = convertToEntity(spaceshipDTO);
		
		Spaceship savedSpaceship = spaceshipRepository.save(spaceship);
		
		spaceshipDTO.setId(savedSpaceship.getId());
		
		return spaceshipDTO;
	}

	@Override
	public List<SpaceshipDTO> getSpaceships() {
		List<Spaceship> spaceships = spaceshipRepository.findAll();
		List<SpaceshipDTO> spaceshipsDTO = new ArrayList<SpaceshipDTO>();
		
		for(Spaceship spaceship: spaceships) {
			SpaceshipDTO spaceshipDTO = convertToDTO(spaceship);
			
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
		
		SpaceshipDTO spaceshipDTO = convertToDTO(victimSpaceship);
		
		return spaceshipDTO;
	}
}
