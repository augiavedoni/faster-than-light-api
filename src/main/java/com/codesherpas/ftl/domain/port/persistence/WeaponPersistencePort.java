package com.codesherpas.ftl.domain.port.persistence;

import com.codesherpas.ftl.domain.dto.WeaponDTO;

public interface WeaponPersistencePort {
	WeaponDTO saveWeapon(WeaponDTO weaponDTO);
}
