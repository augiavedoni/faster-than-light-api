package com.codesherpas.ftl.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesherpas.ftl.infrastructure.entity.SpaceshipEntity;

public interface SpaceshipRepository extends JpaRepository<SpaceshipEntity, Long> {

}
