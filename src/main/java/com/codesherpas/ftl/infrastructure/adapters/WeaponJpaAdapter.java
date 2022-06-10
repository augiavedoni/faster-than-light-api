package com.codesherpas.ftl.infrastructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;

import com.codesherpas.ftl.domain.data.Weapon;
import com.codesherpas.ftl.domain.dto.WeaponDTO;
import com.codesherpas.ftl.domain.exception.ResourceNotFoundException;
import com.codesherpas.ftl.domain.port.persistence.WeaponPersistencePort;
import com.codesherpas.ftl.infrastructure.entity.WeaponEntity;
import com.codesherpas.ftl.infrastructure.mappers.WeaponMapper;
import com.codesherpas.ftl.infrastructure.repository.WeaponRepository;

public class WeaponJpaAdapter implements WeaponPersistencePort {
	@Autowired
	WeaponRepository weaponRepository;

	@Override
	public WeaponDTO saveWeapon(WeaponDTO weaponDTO) {
		WeaponEntity weapon = WeaponMapper.INSTANCE.convertToEntity(weaponDTO);
		WeaponEntity savedWeapon = weaponRepository.save(weapon);
		
		return  WeaponMapper.INSTANCE.convertToDto(savedWeapon);
	}

	@Override
	public Weapon getWeaponById(long weaponId) {
		WeaponEntity weapon = weaponRepository.findById(weaponId)
				.orElseThrow(() -> new ResourceNotFoundException("Weapon", "id", weaponId));
		
		return WeaponMapper.INSTANCE.convertToData(weapon);
	}
}
