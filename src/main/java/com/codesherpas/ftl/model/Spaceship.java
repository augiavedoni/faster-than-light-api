package com.codesherpas.ftl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.codesherpas.ftl.exception.SpaceshipBadParametersException;

import lombok.Data;

@Data
@Entity
@Table(name = "spaceships")
public class Spaceship {
	public Spaceship() {
	}

	public Spaceship(String name, Integer health) throws SpaceshipBadParametersException {
		if(name == null || health == null) {
			throw new SpaceshipBadParametersException();
		}
		
		this.name = name;
		this.health = health;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "health", nullable = false)
	private Integer health;
}
