package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.repository.UserRepository;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public MovieDto findNextMovieInWishlist(Long userId) {
        return null;
    }

    @Override
    public List<MovieDto> getWatchedMovies(Long userId) {
        return null;
    }

    @Override
    public List<MovieDto> getWishList(Long userId) {
        return null;
    }
}
