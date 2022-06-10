package com.codesherpas.ftl.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.codesherpas.ftl.domain.data.Weapon;
import com.codesherpas.ftl.domain.dto.WeaponDTO;
import com.codesherpas.ftl.infrastructure.entity.WeaponEntity;

@Mapper
public interface WeaponMapper {
	WeaponMapper INSTANCE = Mappers.getMapper(WeaponMapper.class);
	
	WeaponDTO convertToDto(WeaponEntity weapon);
	Weapon convertToData(WeaponEntity weapon);
	WeaponEntity convertToEntity(WeaponDTO weapon);
}
