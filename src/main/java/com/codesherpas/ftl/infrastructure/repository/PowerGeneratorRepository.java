package com.codesherpas.ftl.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesherpas.ftl.infrastructure.entity.PowerGeneratorEntity;

public interface PowerGeneratorRepository extends JpaRepository<PowerGeneratorEntity, Long> {

}
