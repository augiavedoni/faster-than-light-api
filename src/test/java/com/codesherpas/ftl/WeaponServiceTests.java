package com.codesherpas.ftl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codesherpas.ftl.domain.exception.BadParameterException;
import com.codesherpas.ftl.domain.service.impl.WeaponServiceImpl;
import com.codesherpas.ftl.infrastructure.entity.WeaponEntity;
import com.codesherpas.ftl.infrastructure.repository.WeaponRepository;

@ExtendWith(MockitoExtension.class)
public class WeaponServiceTests {
	@InjectMocks
	private WeaponServiceImpl weaponService;
	
	@Mock
	private WeaponRepository weaponRepository;
	
	@Test
	public void whenSaveWeapon_receiveSavedWeapon() {
		WeaponEntity weapon = new WeaponEntity(1L, 5);
		
		when(weaponRepository.save(weapon)).thenReturn(weapon);
		
		WeaponEntity savedWeapon = weaponRepository.save(weapon);
		
		assertEquals(savedWeapon.getId(), weapon.getId());
		assertEquals(savedWeapon.getPowerNeeded(), weapon.getPowerNeeded());
	}
	
	@Test
	public void whenSaveWeapon_receiveBadParameterException() {
		BadParameterException exception = Assertions.assertThrows(BadParameterException.class, () -> {
			WeaponEntity weapon = new WeaponEntity(1L, -1);
			
			when(weaponRepository.save(weapon)).thenThrow(new BadParameterException("power-needed", weapon.getPowerNeeded()));
			
			weaponRepository.save(weapon);
		});
		
		assertEquals(exception.getClass(), BadParameterException.class);
		assertEquals(exception.getMessage(), "The field power-needed cannot be -1");
	}
}
