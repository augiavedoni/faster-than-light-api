package com.codesherpas.ftl.domain.data;

import lombok.Data;

@Data
public class PowerGenerator {
	private long id;
	private Integer totalPower;
	private Integer powerNotInUse;
	
	PowerGenerator() {}
	
	public PowerGenerator(long id, Integer totalPower, Integer powerNotInUse) {
		super();
		this.id = id;
		this.totalPower = totalPower;
		this.powerNotInUse = powerNotInUse;
	}
}