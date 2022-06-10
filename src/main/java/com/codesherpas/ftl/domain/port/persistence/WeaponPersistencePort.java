package com.codesherpas.ftl.domain.port.persistence;

import com.codesherpas.ftl.domain.data.Weapon;
import com.codesherpas.ftl.domain.dto.WeaponDTO;

public interface WeaponPersistencePort {
	WeaponDTO saveWeapon(WeaponDTO weapon);
	Weapon getWeaponById(long weaponId);
}
