package com.capstone.capstone1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.capstone1.model.Users;
import com.capstone.capstone1.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public Optional<Users> findByEmail(String email) {
		return repository.findEmail(email);
	}

	@Override
	public List<Users> findByLastName(String lname) {
		return repository.findByLastName(lname);
	}
	
	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Users> findByid(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Users users) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Users users) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
