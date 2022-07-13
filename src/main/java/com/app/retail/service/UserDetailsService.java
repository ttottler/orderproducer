package com.app.retail.service;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.retail.model.AuthRequest;
import com.app.retail.model.User;
import com.app.retail.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(Objects.isNull(user)) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
	
	public void createUser(AuthRequest authRequest) {
		int seq = sequenceGeneratorService.getSequenceNumber("user_sequence");
		User user = new User();
		user.setId(seq);
		user.setEmail(authRequest.getEmail());
		user.setUserName(authRequest.getUserName());
		user.setPassword(authRequest.getPassword());
		userRepository.save(user);
	}

}
