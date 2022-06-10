package com.codesherpas.ftl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import com.codesherpas.ftl.application.controller.SpaceshipController;
import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.dto.SpaceshipDTO;
import com.codesherpas.ftl.domain.dto.WeaponDTO;
import com.codesherpas.ftl.domain.exception.BadParameterException;
import com.codesherpas.ftl.domain.exception.DestroyedSpaceshipException;
import com.codesherpas.ftl.domain.exception.ExcessivePowerEnergyConsumptionException;
import com.codesherpas.ftl.domain.exception.MissingPowerException;
import com.codesherpas.ftl.domain.exception.ResourceNotFoundException;
import com.codesherpas.ftl.domain.port.service.SpaceshipServicePort;
import com.codesherpas.ftl.infrastructure.entity.PowerGeneratorEntity;
import com.codesherpas.ftl.infrastructure.entity.SpaceshipEntity;
import com.codesherpas.ftl.infrastructure.entity.WeaponEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = SpaceshipController.class)
public class SpaceshipControllerTests {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private SpaceshipServicePort service;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void whenPostValidSpaceship_thenReceiveCreated() throws Exception {
		final SpaceshipDTO destructorDTO = new SpaceshipDTO(1L, "Destructor", 100, new WeaponDTO(1L, 5), new PowerGeneratorDTO(1L, 10, 5, 5));
		final String expectedResponseContent = objectMapper.writeValueAsString(destructorDTO);

		given(service.saveSpaceship(destructorDTO)).willReturn(destructorDTO);

		mvc.perform(
				post("/api/spaceships")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(destructorDTO)))
				.andExpect(status().isCreated())
				.andExpect(content().json(expectedResponseContent));

		verify(service, VerificationModeFactory.times(1)).saveSpaceship(destructorDTO);
	}

	@Test
	public void whenPostInvalidSpaceship_thenReceiveBadRequest() throws Exception {
		final SpaceshipDTO destructorDTO = new SpaceshipDTO(1L, "Destructor", null, null, null);

		given(service.saveSpaceship(destructorDTO)).willThrow(new BadParameterException("health", destructorDTO.getHealth()));

		mvc.perform(
				post("/api/spaceships")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(destructorDTO)))
				.andExpect(status().isBadRequest());

		verify(service, VerificationModeFactory.times(1)).saveSpaceship(destructorDTO);
	}
	
	@Test
	public void whenPostInvalidSpaceship_receiveExcessivePowerEnergyConsumptionException() throws Exception {
		final SpaceshipDTO destructorDTO = new SpaceshipDTO(1L, "Destructor", 100, new WeaponDTO(1L, 5), new PowerGeneratorDTO(1L, 10, 4, 6));
		
		given(service.saveSpaceship(destructorDTO)).willThrow(new ExcessivePowerEnergyConsumptionException("weapon", destructorDTO.getPowerGenerator().getPowerConsumedByWeapon(), destructorDTO.getWeapon().getPowerNeeded()));
		
		mvc.perform(
				post("/api/spaceships")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(destructorDTO)))
				.andExpect(status().isPreconditionFailed());
		
		verify(service, VerificationModeFactory.times(1)).saveSpaceship(destructorDTO);
	}

	@Test
	public void whenGetAllSpaceships_thenReceiveListOfSpaceships() throws Exception {
		final SpaceshipDTO destructorDTO = new SpaceshipDTO(1L, "Destructor", 100, new WeaponDTO(1L, 5), new PowerGeneratorDTO(1L, 10, 5, 5));
		final SpaceshipDTO fireDTO = new SpaceshipDTO(2L, "Fire", 100, new WeaponDTO(2L, 5), new PowerGeneratorDTO(2L, 10, 5, 5));
	    final List<SpaceshipDTO> spaceships = Arrays.asList(destructorDTO, fireDTO);

	    given(service.getSpaceships()).willReturn(spaceships);

	    mvc.perform(get("/api/spaceships")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].name", is(destructorDTO.getName())))
	      .andExpect(jsonPath("$[1].name", is(fireDTO.getName())));
	    
	    verify(service, VerificationModeFactory.times(1)).getSpaceships();
	}
	
	@Test
	public void whenShootSpaceship_thenReceiveShooted() throws Exception {
		final SpaceshipDTO destructorDTO = new SpaceshipDTO(1L, "Destructor", 100, new WeaponDTO(1L, 5), new PowerGeneratorDTO(1L, 10, 5, 5));
		final SpaceshipDTO fireDTO = new SpaceshipDTO(2L, "Fire", 100, new WeaponDTO(2L, 5), new PowerGeneratorDTO(2L, 10, 5, 5));
		final SpaceshipDTO attackedFireDTO = new SpaceshipDTO(2L, "Fire", 99, new WeaponDTO(2L, 5), new PowerGeneratorDTO(2L, 10, 5, 5));
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("attackerId", String.valueOf(destructorDTO.getId()));
		params.add("victimId", String.valueOf(fireDTO.getId()));
		
		given(service.shootSpaceship(destructorDTO.getId(), fireDTO.getId())).willReturn(attackedFireDTO);
		
		mvc.perform(
				patch("/api/spaceships/shoot")
				.params(params)
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(jsonPath("$.id", is(2)))
			    .andExpect(jsonPath("$.health", is(99)));
		
		verify(service, VerificationModeFactory.times(1)).shootSpaceship(destructorDTO.getId(), fireDTO.getId());
	}
	
	@Test
	public void whenShootSpaceship_thenReceiveResourceNotFoundException() throws Exception {
		final LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("attackerId", "1");
		params.add("victimId", "4");
		
		given(service.shootSpaceship(1L, 4L)).willThrow(new ResourceNotFoundException("Spaceship", "id", 1L));
		
		mvc.perform(
				patch("/api/spaceships/shoot")
				.params(params)
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isNotFound());
	}
	
	@Test
	public void whenShootSpaceship_thenReceiveDestroyedSpaceshipException() throws Exception {
		SpaceshipEntity destructor = new SpaceshipEntity("Destructor", 100, new WeaponEntity(), new PowerGeneratorEntity(1L, 10, 5, 5));
		destructor.setHealth(0);
		
		final LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("attackerId", String.valueOf(destructor.getId()));
		params.add("victimId", "4");
		
		given(service.shootSpaceship(destructor.getId(), 4)).willThrow(new DestroyedSpaceshipException(destructor.getName()));
		
		mvc.perform(
				patch("/api/spaceships/shoot")
				.params(params)
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isBadRequest());
	}
	
	@Test void whenShootSpaceship_receiveMissingPowerException() throws Exception {
		final SpaceshipDTO destructorDTO = new SpaceshipDTO(1L, "Destructor", 100, new WeaponDTO(1L, 5), new PowerGeneratorDTO(1L, 10, 6, 4));
		
		final LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("attackerId", String.valueOf(destructorDTO.getId()));
		params.add("victimId", "2");
		
		given(service.shootSpaceship(1L, 2L)).willThrow(new MissingPowerException("weapon", destructorDTO.getWeapon().getPowerNeeded(), destructorDTO.getPowerGenerator().getPowerConsumedByWeapon()));
		
		mvc.perform(
				patch("/api/spaceships/shoot")
				.params(params)
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isPreconditionFailed());
	}
}
