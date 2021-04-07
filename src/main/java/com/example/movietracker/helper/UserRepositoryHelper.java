package com.example.movietracker.helper;

import com.example.movietracker.entity.User;
import com.example.movietracker.exception.NotFoundException;
import com.example.movietracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryHelper {

    private final UserRepository userRepository;

    public User ensureUserExists(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
