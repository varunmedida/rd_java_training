package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserName(String userName);
	
}
