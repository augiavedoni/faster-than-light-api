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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import com.codesherpas.ftl.controller.SpaceshipController;
import com.codesherpas.ftl.dto.SpaceshipDTO;
import com.codesherpas.ftl.exception.BadParameterException;
import com.codesherpas.ftl.exception.DestroyedSpaceshipException;
import com.codesherpas.ftl.exception.ResourceNotFoundException;
import com.codesherpas.ftl.model.PowerGenerator;
import com.codesherpas.ftl.model.Spaceship;
import com.codesherpas.ftl.model.Weapon;
import com.codesherpas.ftl.service.SpaceshipService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = SpaceshipController.class)
public class SpaceshipControllerTests {
	private ModelMapper mapper =  new ModelMapper();
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private SpaceshipService service;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void whenPostValidSpaceship_thenReceiveCreated() throws Exception {
		Spaceship destructor = new Spaceship("Destructor", 100, new Weapon(), new PowerGenerator(1L, 200, 200));
		SpaceshipDTO destructorDTO = mapper.map(destructor, SpaceshipDTO.class);
		final String expectedResponseContent = objectMapper.writeValueAsString(destructor);

		given(service.saveSpaceship(destructorDTO)).willReturn(destructorDTO);

		mvc.perform(
				post("/api/spaceships")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(destructor)))
				.andExpect(status().isCreated())
				.andExpect(content().json(expectedResponseContent));

		verify(service, VerificationModeFactory.times(1)).saveSpaceship(destructorDTO);
	}

	@Test
	public void whenPostInvalidSpaceship_thenReceiveBadRequest() throws Exception {
		Spaceship destructor = new Spaceship("Destructor", null, null, null);
		SpaceshipDTO destructorDTO = mapper.map(destructor, SpaceshipDTO.class);

		given(service.saveSpaceship(destructorDTO)).willThrow(new BadParameterException("health", destructorDTO.getHealth()));

		mvc.perform(
				post("/api/spaceships")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(destructor)))
				.andExpect(status().isBadRequest());

		verify(service, VerificationModeFactory.times(1)).saveSpaceship(destructorDTO);
	}

	@Test
	public void whenGetAllSpaceships_thenReceiveListOfSpaceships() throws Exception {
    	Spaceship destructor = new Spaceship("Destructor", 100, new Weapon(), new PowerGenerator(1L, 200, 200));
    	SpaceshipDTO destructorDTO = mapper.map(destructor, SpaceshipDTO.class);
    	Spaceship fire = new Spaceship("Fire", 100, new Weapon(), new PowerGenerator(2L, 200, 200));
    	SpaceshipDTO fireDTO = mapper.map(fire, SpaceshipDTO.class);

	    List<SpaceshipDTO> spaceships = Arrays.asList(destructorDTO, fireDTO);

	    given(service.getSpaceships()).willReturn(spaceships);

	    mvc.perform(get("/api/spaceships")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].name", is(destructor.getName())))
	      .andExpect(jsonPath("$[1].name", is(fire.getName())));
	    
	    verify(service, VerificationModeFactory.times(1)).getSpaceships();
	}
	
	@Test
	public void whenShootSpaceship_thenReceiveShooted() throws Exception {
		Spaceship destructor = new Spaceship("Destructor", 100, new Weapon(), new PowerGenerator(1L, 200, 200));
		SpaceshipDTO destructorDTO = mapper.map(destructor, SpaceshipDTO.class);
		Spaceship fire = new Spaceship("Fire", 99, new Weapon(), new PowerGenerator(2L, 200, 200));
		fire.setId(1);
		SpaceshipDTO fireDTO = mapper.map(fire, SpaceshipDTO.class);
		
		final LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("attackerId", String.valueOf(destructor.getId()));
		params.add("victimId", String.valueOf(fire.getId()));
		
		given(service.shootSpaceship(destructorDTO.getId(), fireDTO.getId())).willReturn(fireDTO);
		
		mvc.perform(
				patch("/api/spaceships/shoot")
				.params(params)
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(jsonPath("$.health", is(99)))
			    .andExpect(jsonPath("$.id", is(1)));
		
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
		Spaceship destructor = new Spaceship("Destructor", 100, new Weapon(), new PowerGenerator(1L, 200, 200));
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
}
