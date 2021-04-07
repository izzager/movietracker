package com.example.movietracker.security;

import org.springframework.security.core.Authentication;

public interface UserContext {
    Authentication getAuthentication();
}
