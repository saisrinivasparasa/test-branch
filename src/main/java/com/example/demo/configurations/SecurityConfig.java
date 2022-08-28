package com.example.demo.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomAuthentication;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomAuthentication customAuth;
	
//	@Bean
//	protected InMemoryUserDetailsManager authorizationRoles() {
//		List<UserDetails> users= new ArrayList<>();
//		List<GrantedAuthority> grantedAuthorities= new ArrayList<>();
//		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//		List<GrantedAuthority> adminAuthorities= new ArrayList<>();
//		adminAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
//		users.add(new User("shiva", "pass", grantedAuthorities));
//		users.add(new User("sai","pass",adminAuthorities));
//		return new InMemoryUserDetailsManager(users);
//	}
	
	
	
	@Bean
	protected BCryptPasswordEncoder bcryptEncoding() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	protected SecurityFilterChain authenticationAccess(HttpSecurity http ) throws Exception {
//		http.authorizeRequests().antMatchers("/userLogin").hasAnyAuthority("USER", "ADMIN").antMatchers("/adminLogin")
//				.hasAuthority("ADMIN").antMatchers("/helloWorld").permitAll().and().formLogin();
//		http.authenticationProvider(customAuth);
//		return http.build();
//		
//	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(customAuth);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/userLogin").hasAnyAuthority("USER", "ADMIN").antMatchers("/adminLogin")
				.hasAuthority("ADMIN").antMatchers("/helloWorld").permitAll().and().formLogin();
	}
}
