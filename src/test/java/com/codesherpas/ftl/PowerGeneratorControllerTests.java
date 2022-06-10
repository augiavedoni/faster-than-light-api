package com.codesherpas.ftl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import com.codesherpas.ftl.application.controller.PowerGeneratorController;
import com.codesherpas.ftl.domain.dto.PowerGeneratorDTO;
import com.codesherpas.ftl.domain.dto.WeaponDTO;
import com.codesherpas.ftl.domain.exception.BadParameterException;
import com.codesherpas.ftl.domain.exception.ExcessivePowerEnergyConsumptionException;
import com.codesherpas.ftl.domain.port.service.PowerGeneratorServicePort;

@WebMvcTest(value = PowerGeneratorController.class)
public class PowerGeneratorControllerTests {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PowerGeneratorServicePort powerGeneratorService;
	
	@Test
	public void whenReceiveGoodParameters_receiveEditedPowerGenerator() throws Exception {
		final PowerGeneratorDTO powerGenerator = new PowerGeneratorDTO(1L, 10, 5, 5);
		final PowerGeneratorDTO modifiedPowerGenerator = new PowerGeneratorDTO(1L, 10, 6, 4);
		final WeaponDTO weapon = new WeaponDTO(1L, 5);
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("powerGeneratorId", String.valueOf(powerGenerator.getId()));
		params.add("weaponId", String.valueOf(weapon.getId()));
		params.add("newPowerConsumedByWeapon", "4");
		
		given(powerGeneratorService.editPowerConsumedByWeapon(powerGenerator.getId(), weapon.getId(), 4)).willReturn(modifiedPowerGenerator);
		
		mvc.perform(
				patch("/api/spaceships/power-generator/")
				.params(params)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(powerGenerator)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.total-power", is(10)))
				.andExpect(jsonPath("$.power-not-in-use", is(6)))
				.andExpect(jsonPath("$.power-consumed-by-weapon", is(4)));
		
		verify(powerGeneratorService, VerificationModeFactory.times(1)).editPowerConsumedByWeapon(powerGenerator.getId(), weapon.getId(), 4);
	}
	
	@Test
	public void whenReceiveBadParameters_receiveBadParameterException() throws Exception {
		final PowerGeneratorDTO powerGenerator = new PowerGeneratorDTO(1L, 5, 5, 5);
		final WeaponDTO weapon = new WeaponDTO(1L, 5);
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("powerGeneratorId", String.valueOf(powerGenerator.getId()));
		params.add("weaponId", String.valueOf(weapon.getId()));
		params.add("newPowerConsumedByWeapon", "-1");
		
		given(powerGeneratorService.editPowerConsumedByWeapon(powerGenerator.getId(), weapon.getId(), -1)).willThrow(new BadParameterException("newPowerConsumedByWeapon", "-1"));
		
		mvc.perform(
				patch("/api/spaceships/power-generator/")
				.params(params)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(powerGenerator)))
				.andExpect(status().isBadRequest());
		
		verify(powerGeneratorService, VerificationModeFactory.times(1)).editPowerConsumedByWeapon(powerGenerator.getId(), weapon.getId(), -1);
	}
	
	@Test
	public void whenReceiveBadParameters_receiveExcesivePowerEnergyConsumptionException() throws Exception {
		final PowerGeneratorDTO powerGenerator = new PowerGeneratorDTO(1L, 5, 5, 5);
		final WeaponDTO weapon = new WeaponDTO(1L, 5);
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("powerGeneratorId", String.valueOf(powerGenerator.getId()));
		params.add("weaponId", String.valueOf(weapon.getId()));
		params.add("newPowerConsumedByWeapon", "20");
		
		given(powerGeneratorService.editPowerConsumedByWeapon(powerGenerator.getId(), weapon.getId(), 20)).willThrow(new ExcessivePowerEnergyConsumptionException("weapon", 20, weapon.getPowerNeeded()));
		
		mvc.perform(
				patch("/api/spaceships/power-generator/")
				.params(params)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(powerGenerator)))
				.andExpect(status().isPreconditionFailed());
		
		verify(powerGeneratorService, VerificationModeFactory.times(1)).editPowerConsumedByWeapon(powerGenerator.getId(), weapon.getId(), 20);
	}
}
