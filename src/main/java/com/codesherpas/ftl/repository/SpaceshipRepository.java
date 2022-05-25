package com.codesherpas.ftl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesherpas.ftl.model.Spaceship;

public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {

}
