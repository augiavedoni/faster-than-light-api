package com.codesherpas.ftl.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.port.service.PowerGeneratorServicePort;

@RestController
@RequestMapping("/api/spaceships/power-generator")
public class PowerGeneratorController {
	@Autowired
	PowerGeneratorServicePort powerGeneratorService;
	
	@PatchMapping()
	public ResponseEntity<PowerGeneratorDTO> editPowerConsumedByWeapon(@RequestParam Map<String, String> information) {
		final long powerGeneratorId = Long.parseLong(information.get("powerGeneratorId"));
		final long weaponId = Long.parseLong(information.get("weaponId"));
		final Integer newPowerConsumedByWeapon = Integer.parseInt(information.get("newPowerConsumedByWeapon"));
		
		return new ResponseEntity<PowerGeneratorDTO>(powerGeneratorService.
				editPowerConsumedByWeapon(powerGeneratorId, weaponId, newPowerConsumedByWeapon), HttpStatus.OK);
	}
}
