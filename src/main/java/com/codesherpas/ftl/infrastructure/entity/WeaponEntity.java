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
@Table(name = "weapons")
public class WeaponEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "power_needed", nullable = false)
	private Integer powerNeeded;
	
	public WeaponEntity() {}
	
	public WeaponEntity(long id, Integer powerNeeded) {
		this.id = id;
		this.powerNeeded = powerNeeded;
	}
}
