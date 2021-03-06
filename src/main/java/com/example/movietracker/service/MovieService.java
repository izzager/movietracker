package com.example.movietracker.service;

import com.example.movietracker.dto.MovieDto;

public interface MovieService {
    MovieDto markAsWatched(Long movieId, Long userId);
    MovieDto addToWishlist(Long movieId, Long userId);
    MovieDto removeFromWishlist(Long movieId, Long userId);
}
