package com.codesherpas.ftl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codesherpas.ftl.domain.port.persistence.PowerGeneratorPersistencePort;
import com.codesherpas.ftl.domain.port.persistence.SpaceshipPersistencePort;
import com.codesherpas.ftl.domain.port.persistence.WeaponPersistencePort;
import com.codesherpas.ftl.domain.port.service.PowerGeneratorServicePort;
import com.codesherpas.ftl.domain.port.service.SpaceshipServicePort;
import com.codesherpas.ftl.domain.port.service.WeaponServicePort;
import com.codesherpas.ftl.domain.service.impl.PowerGeneratorServiceImpl;
import com.codesherpas.ftl.domain.service.impl.SpaceshipServiceImpl;
import com.codesherpas.ftl.domain.service.impl.WeaponServiceImpl;
import com.codesherpas.ftl.infrastructure.adapters.PowerGeneratorJpaAdapter;
import com.codesherpas.ftl.infrastructure.adapters.SpaceshipJpaAdapter;
import com.codesherpas.ftl.infrastructure.adapters.WeaponJpaAdapter;

@Configuration
public class ApplicationConfig {
	@Bean
	public WeaponPersistencePort weaponPersistence() {
		return new WeaponJpaAdapter();
	}
	
	@Bean
	public WeaponServicePort weaponService() {
		return new WeaponServiceImpl(weaponPersistence());
	}
	
	@Bean
	public PowerGeneratorPersistencePort powerGeneratorPersistence() {
		return new PowerGeneratorJpaAdapter();
	}
	
	@Bean
	public PowerGeneratorServicePort powerGeneratorService() {
		return new PowerGeneratorServiceImpl(powerGeneratorPersistence(), weaponPersistence());
	}
	
	@Bean
	public SpaceshipPersistencePort spaceshipPersistence() {
		return new SpaceshipJpaAdapter();
	}
	
	@Bean
	public SpaceshipServicePort spaceshipService() {
		return new SpaceshipServiceImpl(spaceshipPersistence(), powerGeneratorService(), weaponService());
	}
}