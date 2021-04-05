package com.example.movietracker.service;

import com.example.movietracker.dto.MovieDto;

import java.util.List;

public interface UserService {
    MovieDto findNextMovieInWishlist(Long userId);
    List<MovieDto> getWatchedMovies(Long userId);
    List<MovieDto> getWishList(Long userId);
}
