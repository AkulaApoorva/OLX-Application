package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.User;
import com.olx.security.JwtUtil;
import com.olx.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/olx/user")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	LoginService service;
	@GetMapping(value="/validate/token")
	public ResponseEntity<Boolean> isValidateUser(@RequestHeader("Authorization")String authToken)
	{
		//Business logic steps
		// 1) Extract 'Bearer ' word from the token using substring() method
		String jwtToken=authToken.substring(7,authToken.length());
		// 2) Validate the token using JwtUtil.validateToken(xx) method
		String clientUsername=jwtUtil.extractUsername(jwtToken);
		String databaseUsername=service.loadUserByUsername(clientUsername).getUsername();
		boolean isValidToken=jwtUtil.validateToken(jwtToken, databaseUsername);
		// 3) Return result true/false to the client
		if(isValidToken)
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
	}
	

	
	@PostMapping(value="/authenticate/", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException ex)		//login failes
		{
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//login successfull... now return auth-token
		String jwtToken=jwtUtil.generateTokenByUsername(authenticationRequest.getUsername());
		return new ResponseEntity<String>(jwtToken,HttpStatus.OK);
		
	}
	
	
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Creating New User", notes="This service creates new User")
	public ResponseEntity<User> create(@RequestBody User user){
		return service.createNewUser(user);
	}
	
	@GetMapping("/user/{id}")
	@ApiOperation(value="Provide Existing User", notes="This service provides particular User")
	public ResponseEntity<User> read(@ApiParam(value="User Identifiers",required = true) @PathVariable String id){
		return service.getUserById(id);
	}
	
	@PostMapping("/user/authenticate")
	@ApiOperation(value="Login User", notes="This is Login User Service")
	public ResponseEntity<String> auth(){
			return service.auth();
	}
	
	@DeleteMapping("/user/logout")
	@ApiOperation(value="Logout", notes="This is Logout Service")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
			return service.logout(token);
	}

}
