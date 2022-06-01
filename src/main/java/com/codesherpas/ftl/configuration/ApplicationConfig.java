package com.codesherpas.ftl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codesherpas.ftl.domain.port.persistence.PowerGeneratorPersistencePort;
import com.codesherpas.ftl.domain.port.persistence.SpaceshipPersistencePort;
import com.codesherpas.ftl.domain.port.service.PowerGeneratorServicePort;
import com.codesherpas.ftl.domain.port.service.SpaceshipServicePort;
import com.codesherpas.ftl.domain.service.impl.PowerGeneratorServiceImpl;
import com.codesherpas.ftl.domain.service.impl.SpaceshipServiceImpl;
import com.codesherpas.ftl.infrastructure.adapters.PowerGeneratorJpaAdapter;
import com.codesherpas.ftl.infrastructure.adapters.SpaceshipJpaAdapter;

@Configuration
public class ApplicationConfig {
	@Bean
	public PowerGeneratorPersistencePort powerGeneratorPersistence() {
		return new PowerGeneratorJpaAdapter();
	}
	
	@Bean
	public PowerGeneratorServicePort powerGeneratorService() {
		return new PowerGeneratorServiceImpl(powerGeneratorPersistence());
	}
	
	@Bean
	public SpaceshipPersistencePort spaceshipPersistence() {
		return new SpaceshipJpaAdapter();
	}
	
	@Bean
	public SpaceshipServicePort spaceshipService() {
		return new SpaceshipServiceImpl(spaceshipPersistence(), powerGeneratorService());
	}
}