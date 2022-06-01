package com.codesherpas.ftl.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesherpas.ftl.infrastructure.entity.WeaponEntity;

public interface WeaponRepository extends JpaRepository<WeaponEntity, Long> {

}
