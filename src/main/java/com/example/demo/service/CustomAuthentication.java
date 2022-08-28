package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.userDetailsImpl.CustomerDetails;

@Service
public class CustomAuthentication implements AuthenticationProvider{
	
	@Autowired
	private JpaUserService jpaUserService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=authentication.getName();
		String password = authentication.getCredentials().toString();
		CustomerDetails fetchUser= jpaUserService.loadUserByUsername(username);
		if((fetchUser.getUser().getAlgorithm()).toString() == "BCRYPT") {
			return checkPassword(fetchUser,password,bcrypt);
		}
		else {
			throw new BadCredentialsException("Invalid Credentails");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
	}

	private Authentication checkPassword(CustomerDetails user, 
			 String rawPassword, 
			 PasswordEncoder encoder){
			 if (encoder.matches(rawPassword, user.getPassword())) {
				  return  new UsernamePasswordAuthenticationToken(
						 user.getUsername(), 
						 user.getPassword(), 
						 user.getAuthorities());
			 }else {
				 throw new BadCredentialsException("Token Creation Error!");
			 }
			
	}
	
	
}
