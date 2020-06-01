package com.insulinbond.users.model;

import java.util.UUID;

/**
 * Authentication Response
 */
public class AuthenticationResponse {
	// UUID Context
	private final UUID context;

	// Java Web Token
	private final String jwt;

	// constructor that return the JWT and context in response after signing in
	public AuthenticationResponse(String jwt, UUID context) {
		this.jwt = jwt;
		this.context = context;
	}


	// Getters
	public String getJwt() {
		return jwt;
	}

	public UUID getContext() {
		return context;
	}
}
