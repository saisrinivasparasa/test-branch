package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Integer>{
	
	 Optional<UserInfo> findByUsername(String u);
}
