package com.app.retail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.retail.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	public User findByUserName(String username);
	
}
