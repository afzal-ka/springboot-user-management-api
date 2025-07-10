package com.usermanagement.api.service;

import com.usermanagement.api.entity.User;
import com.usermanagement.api.exception.UserNotFoundException;
import com.usermanagement.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existedUser = repository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found with email: " + email));
        return new UserPrincipal(existedUser);
    }
}
