package com.olx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.dto.User;
import com.olx.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer>
{
	List<UserEntity> findByUsername(String username);

	Optional<User> findById(String id);

	void save(User user);

	
}
