package com.collegefes.CollegeFest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.collegefes.CollegeFest.entity.MyUserDetails;
import com.collegefes.CollegeFest.entity.User;
import com.collegefes.CollegeFest.repo.UserRepo;

public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("User Not Found");
		return new MyUserDetails(user);
	}

}
