package com.capstone.capstone1.service;

import java.util.List;
import java.util.Optional;

import com.capstone.capstone1.model.Users;

public interface UserService {
	
	
	Optional<Users> findByid(long id);
	void delete(long id);
	void save(Users users);
	void update(Users users);
	List<Users> findAll();
	
	Optional<Users> findByEmail(String email);
	List<Users>findByLastName(String lname);

}
