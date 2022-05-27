package com.codesherpas.ftl.model;

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
public class PowerGenerator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "total_power", nullable = false)
	private Integer totalPower;
	
	@Column(name = "power_not_in_use", nullable = false)
	private Integer powerNotInUse;
	
	public PowerGenerator() {}

	public PowerGenerator(long id, Integer totalPower, Integer powerNotInUse) {
		super();
		this.id = id;
		this.totalPower = totalPower;
		this.powerNotInUse = powerNotInUse;
	}
}
