package com.example.movietracker.service.impl;

import com.example.movietracker.repository.UserRepository;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

}
