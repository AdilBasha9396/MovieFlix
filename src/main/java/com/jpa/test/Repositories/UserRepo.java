package com.jpa.test.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

}
