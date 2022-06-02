package com.codesherpas.ftl.application.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.domain.port.service.SpaceshipServicePort;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {
	@Autowired
	private SpaceshipServicePort spaceshipService;

	@PostMapping()
	public ResponseEntity<SpaceshipDTO> saveSpaceship(@RequestBody final SpaceshipDTO spaceshipDTO) {
		return new ResponseEntity<SpaceshipDTO>(spaceshipService.saveSpaceship(spaceshipDTO), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<SpaceshipDTO>> getSpaceships() {
		return new ResponseEntity<List<SpaceshipDTO>>(spaceshipService.getSpaceships(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/shoot")
	public ResponseEntity<SpaceshipDTO> shootSpaceship(@RequestParam Map<String, String> spaceshipsId) {
		final long attackerId = Long.parseLong(spaceshipsId.get("attackerId"));
		final long victimId = Long.parseLong(spaceshipsId.get("victimId"));
		
		return new ResponseEntity<SpaceshipDTO>(spaceshipService.shootSpaceship(attackerId, victimId), HttpStatus.OK);
	}
}
