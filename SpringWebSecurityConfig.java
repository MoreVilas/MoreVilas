package com.collegefes.CollegeFest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.collegefes.CollegeFest.entity.Role;
import com.collegefes.CollegeFest.entity.User;
import com.collegefes.CollegeFest.repo.RoleRepo;
import com.collegefes.CollegeFest.repo.UserRepo;
import com.collegefes.CollegeFest.serviceImpl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(authenticationProvider());
	}
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(encoder());
		return dao;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/api/students/findAll","/api/students/findById","/api/students/showformForAdd")
		.hasAnyAuthority("USER","ADMIN")
		.antMatchers(HttpMethod.POST,"/api/students/save")
		.hasAnyAuthority("USER","ADMIN")
		.antMatchers("/api/students/showformForUpdate","/api/students/deleteById")
		.hasAnyAuthority("ADMIN")
		.and().formLogin().loginProcessingUrl("/login").permitAll()
		.and().logout().logoutSuccessUrl("/login").permitAll();
	}
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;
	@Bean
	public void addData() {
		Role r1 = new Role();
		r1.setName("USER");
		r1.setId(1);
		Role r2 = new Role();
		r2.setId(2);
		r2.setName("ADMIN");
		roleRepo.save(r1);
		roleRepo.save(r2);
		
		User u1 = new User(1, "vilas", encoder().encode("vilas123"), Arrays.asList(r1));
		User u2 = new User(2, "admin", encoder().encode("admin123"), Arrays.asList(r1,r2));
	 
//		userRepo.save(u1);
//		userRepo.save(u2);
	}

}
