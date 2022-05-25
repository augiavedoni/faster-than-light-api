package com.codesherpas.ftl.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesherpas.ftl.exception.SpaceshipBadParametersException;
import com.codesherpas.ftl.model.Spaceship;
import com.codesherpas.ftl.resources.SpaceshipResource;
import com.codesherpas.ftl.service.SpaceshipService;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {
	private SpaceshipService spaceshipService;

	public SpaceshipController(SpaceshipService spaceshipService) {
		super();
		this.spaceshipService = spaceshipService;
	}

	@PostMapping()
	public ResponseEntity<Spaceship> saveSpaceship(@RequestBody SpaceshipResource spaceship) {
		Spaceship convertedSpaceship = null;
		
		try {
			convertedSpaceship = new Spaceship(spaceship.getName(), spaceship.getHealth());
		} catch (SpaceshipBadParametersException exception) {
			return new ResponseEntity<Spaceship>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Spaceship>(spaceshipService.saveSpaceship(convertedSpaceship), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Spaceship>> getSpaceships() {
		return new ResponseEntity<List<Spaceship>>(spaceshipService.getSpaceships(), HttpStatus.OK);
	}
}
