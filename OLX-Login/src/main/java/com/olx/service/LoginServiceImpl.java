package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.olx.dto.User;
import com.olx.entity.UserEntity;
import com.olx.repository.UserRepository;
import org.modelmapper.ModelMapper;

public class LoginServiceImpl implements LoginService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	List<User> userList = new ArrayList<>();
	List<String> logoutTokenList = new ArrayList<>();
	int userId=0;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<User> createNewUser(User user) {
		userRepository.save(user);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<User> getUserById(String id) {
		Optional<User> users = userRepository.findById(id);
		User user = users.get();
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> auth() {
		return new ResponseEntity<String>("AK47KR",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> logout(String token) {
		logoutTokenList.add(token);
		return new ResponseEntity<String>("true",HttpStatus.OK);
	}
	@Override
	public boolean isLogout(String jwtToken) {
		if(logoutTokenList.contains(jwtToken)) {
			return true;
		}
		else
			return false;
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		List<com.olx.entity.UserEntity> userEntityList=userRepository.findByUsername(username);
		if(userEntityList.size()>0)	//User is found
		{
			com.olx.entity.UserEntity userEntity=userEntityList.get(0);
			List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(userEntity.getRoles()));
			User user=new User(username,passwordEncoder.encode(userEntity.getPassword()),authorities);
			return user;
		}
		throw new UsernameNotFoundException(username);
		
	}


	

}
