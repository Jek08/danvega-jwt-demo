package dev.jakapw.jwt_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jakapw.jwt_demo.service.TokenService;

@RestController
public class AuthController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	private final TokenService tokenService;

	public AuthController(TokenService tokenService) {
		super();
		this.tokenService = tokenService;
	}
	
	@PostMapping("/token")
	public String token(Authentication authentication) {
		LOG.debug("Token requested for user: '{}'", authentication);
		String token = tokenService.generateToken(authentication);
		LOG.debug("Token granted: {}", token);
		return token;
	}
}
