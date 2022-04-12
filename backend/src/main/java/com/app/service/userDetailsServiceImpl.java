package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.pojos.User;
import com.app.repository.UserRepository;
@Service
@Transactional
public class userDetailsServiceImpl  implements UserDetailsService{

	@Autowired 
	UserRepository userRepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username ));
		return UserDetailsImpl.build(user);
	}

}
