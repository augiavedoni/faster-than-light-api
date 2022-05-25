package com.codesherpas.ftl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.codesherpas.ftl.controller.SpaceshipController;
import com.codesherpas.ftl.model.Spaceship;
import com.codesherpas.ftl.service.SpaceshipService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SpaceshipController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class FtlApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SpaceshipService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void whenPostValidSpaceship_thenReceiveCreated() throws Exception {
		Spaceship destructor = new Spaceship("Destructor", 100);
		final String expectedResponseContent = objectMapper.writeValueAsString(destructor);

		given(service.saveSpaceship(destructor)).willReturn(destructor);

		mvc.perform(
				post("/api/spaceships").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(destructor)))
				.andExpect(status().isCreated()).andExpect(content().json(expectedResponseContent));

		verify(service, VerificationModeFactory.times(1)).saveSpaceship(destructor);
	}

	@Test
	public void whenPostInvalidSpaceship_thenReceiveBadRequest() throws Exception {
		Spaceship destructor = new Spaceship();

		given(service.saveSpaceship(Mockito.any())).willReturn(null);

		mvc.perform(
				post("/api/spaceships").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(destructor)))
				.andExpect(status().isBadRequest());

		verify(service, VerificationModeFactory.times(0)).saveSpaceship(Mockito.any());

		reset(service);
	}

	@Test
	public void whenGetAllSpaceships_thenReceiveListOfSpaceships() throws Exception {
    	Spaceship destructor = new Spaceship("Destructor", 100);
    	Spaceship fire = new Spaceship("Fire", 100);

	    List<Spaceship> spaceships = Arrays.asList(destructor, fire);

	    given(service.getSpaceships()).willReturn(spaceships);

	    mvc.perform(get("/api/spaceships")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].name", is(destructor.getName())))
	      .andExpect(jsonPath("$[1].name", is(fire.getName())));
	}
}
