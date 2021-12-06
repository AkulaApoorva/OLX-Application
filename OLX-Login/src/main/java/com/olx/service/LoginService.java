package com.olx.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.olx.dto.User;

public interface LoginService {
	ResponseEntity<User> createNewUser(User user);
	ResponseEntity<User> getUserById(String id);
	ResponseEntity<String> auth();
	ResponseEntity<String> logout(String token);
	boolean isLogout(String jwtToken);
	User loadUserByUsername(String clientUsername);

}
