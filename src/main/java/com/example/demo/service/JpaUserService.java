package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserInfo;
import com.example.demo.repo.UserRepo;
import com.example.demo.userDetailsImpl.CustomerDetails;

@Service
public class JpaUserService implements UserDetailsService{

	@Autowired
	UserRepo userRepo;

	@Override
	public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo u= userRepo.findByUsername(username).orElseThrow(null);
		return new CustomerDetails(u);
	}
	
	
	
}
