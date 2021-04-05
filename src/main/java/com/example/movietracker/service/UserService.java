package com.example.movietracker.service;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.User;

import java.util.List;

public interface UserService {
    MovieDto findNextMovieInWishlist(Long userId);
    List<MovieDto> getWatchedMovies(Long userId);
    List<MovieDto> getWishList(Long userId);
    User findByUsername(String username);
}
