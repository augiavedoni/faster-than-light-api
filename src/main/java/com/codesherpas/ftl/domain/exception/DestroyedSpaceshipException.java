package com.codesherpas.ftl.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DestroyedSpaceshipException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String spaceshipName;
	
	public DestroyedSpaceshipException(String spaceshipName) {
		super(String.format("The spaceship %s is destroyed", spaceshipName));
		this.spaceshipName = spaceshipName;
	}

	public String getSpaceshipName() {
		return spaceshipName;
	}
}
