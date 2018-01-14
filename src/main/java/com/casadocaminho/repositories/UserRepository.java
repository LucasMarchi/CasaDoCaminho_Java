package com.casadocaminho.repositories;

import org.springframework.data.repository.CrudRepository;

import com.casadocaminho.models.User;

public interface UserRepository extends CrudRepository<User, String>{
	
	User findByUsername(String email);

}
