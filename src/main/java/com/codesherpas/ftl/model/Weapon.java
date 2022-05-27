package com.codesherpas.ftl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "weapons")
public class Weapon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public Weapon() {}
}
