package com.codesherpas.ftl.domain.dto;

import lombok.Data;

@Data
public class WeaponDTO {
	private long id;
	
	public WeaponDTO() {}
	
	public WeaponDTO(long id) {
		this.id = id;
	}
}
