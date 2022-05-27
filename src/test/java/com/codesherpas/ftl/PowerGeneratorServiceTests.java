package com.codesherpas.ftl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codesherpas.ftl.exception.BadParameterException;
import com.codesherpas.ftl.model.PowerGenerator;
import com.codesherpas.ftl.repository.PowerGeneratorRepository;
import com.codesherpas.ftl.service.impl.PowerGeneratorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PowerGeneratorServiceTests {
	@InjectMocks
	private PowerGeneratorServiceImpl powerGeneratorService;
	
	@Mock
	private PowerGeneratorRepository powerGeneratorRepository;
	
	@Test
	public void whenSavePowerGenerator_receiveSavedPowerGenerator() {
		PowerGenerator powerGenerator = new PowerGenerator(1L, 200, 200);
		
		when(powerGeneratorRepository.save(powerGenerator)).thenReturn(powerGenerator);
		
		PowerGenerator savedPowerGenerator = powerGeneratorRepository.save(powerGenerator);
		
		
		assertEquals(savedPowerGenerator.getId(), powerGenerator.getId());
		assertEquals(savedPowerGenerator.getTotalPower(), powerGenerator.getTotalPower());
		assertEquals(savedPowerGenerator.getPowerNotInUse(), powerGenerator.getPowerNotInUse());
	}
	
	@Test
	public void whenSavePowerGenerator_receiveBadParameterException() {
		BadParameterException exception = Assertions.assertThrows(BadParameterException.class, () -> {
			PowerGenerator powerGenerator = new PowerGenerator(1L, -1, 200);
			
			when(powerGeneratorRepository.save(powerGenerator)).thenThrow(new BadParameterException("total-power", powerGenerator.getTotalPower()));
			
			powerGeneratorRepository.save(powerGenerator); 
		});
		
		assertEquals(exception.getClass(), BadParameterException.class);
		assertEquals(exception.getMessage(), "The field total-power cannot be -1");
	}
}
