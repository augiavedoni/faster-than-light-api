package com.codesherpas.ftl.infrastructure.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "spaceships")
public class SpaceshipEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "health", nullable = false)
	private Integer health;
	
	@OneToOne(targetEntity = WeaponEntity.class, cascade = CascadeType.ALL)
	private WeaponEntity weapon;
	
	@OneToOne(targetEntity = PowerGeneratorEntity.class)
	@JsonProperty("power-generator")
	private PowerGeneratorEntity powerGenerator;
	
	public SpaceshipEntity() {}
	
	public SpaceshipEntity(String name, Integer health, WeaponEntity weapon, PowerGeneratorEntity powerGenerator) {
		this.name = name;
		this.health = health;
		this.weapon = weapon;
		this.powerGenerator = powerGenerator;
	}
}
