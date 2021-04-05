package com.example.movietracker.security;

import com.example.movietracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        com.example.movietracker.entity.User user = userRepository.findUserByUsername(s);
        return new User(user.getUsername(), user.getPassword(),
                true, true,
                true, true, new ArrayList<>());
    }

}
