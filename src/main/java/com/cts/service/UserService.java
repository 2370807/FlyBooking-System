package com.cts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cts.model.Passenger;
import com.cts.model.User;
import com.cts.repository.UserRepository;

@Component
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Passenger> user= userRepository.findByUsername(username);
		return user.map(User::new)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}

}
