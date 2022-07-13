package com.app.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.retail.model.AuthRequest;
import com.app.retail.service.UserDetailsService;
import com.app.retail.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/signin")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {

			userDetailsService.loadUserByUsername(authRequest.getUserName());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			userDetailsService.createUser(authRequest);
		}

		return jwtUtil.generateToken(authRequest.getUserName());
	}

}
