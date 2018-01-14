package com.casadocaminho.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.casadocaminho.models.User;
import com.casadocaminho.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        User user = userRepository.findByUsername(username);
        
        if(user == null) {
			throw new UsernameNotFoundException("O usuário " + username + " não foi encontrado");
		}
        
        return user;

    }
    
    public void cadastrar(User user) {
    	userRepository.save(user);
    }
}
