package com.codesherpas.ftl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesherpas.ftl.model.Weapon;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

}
