package com.codesherpas.ftl.dto;

import lombok.Data;

@Data
public class WeaponDTO {
	private long id;
	
	public WeaponDTO() {}
	
	public WeaponDTO(long id) {
		this.id = id;
	}
}
