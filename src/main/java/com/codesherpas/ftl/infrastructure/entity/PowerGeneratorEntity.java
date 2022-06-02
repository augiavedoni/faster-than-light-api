package com.codesherpas.ftl.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "power_generators")
public class PowerGeneratorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "total_power", nullable = false)
	@JsonProperty("total-power")
	private Integer totalPower;
	
	@Column(name = "power_not_in_use", nullable = false)
	@JsonProperty("power-not-in-use")
	private Integer powerNotInUse;
	
	public PowerGeneratorEntity() {}

	public PowerGeneratorEntity(long id, Integer totalPower, Integer powerNotInUse) {
		this.id = id;
		this.totalPower = totalPower;
		this.powerNotInUse = powerNotInUse;
	}
}
