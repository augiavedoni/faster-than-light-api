package com.codesherpas.ftl.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "power_generators")
public class PowerGeneratorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "total_power", nullable = false)
	private Integer totalPower;
	
	@Column(name = "power_not_in_use", nullable = false)
	private Integer powerNotInUse;
	
	@Column(name = "power_consumed_by_weapon", nullable = false)
	private Integer powerConsumedByWeapon;
	
	public PowerGeneratorEntity() {}

	public PowerGeneratorEntity(long id, Integer totalPower, Integer powerNotInUse, Integer powerConsumedByWeapon) {
		this.id = id;
		this.totalPower = totalPower;
		this.powerNotInUse = powerNotInUse;
		this.powerConsumedByWeapon = powerConsumedByWeapon;
	}
}
