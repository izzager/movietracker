package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.ResourceForbiddenException;
import com.example.movietracker.mapper.MovieMapper;
import com.example.movietracker.repository.UserRepository;
import com.example.movietracker.service.MovieService;
import com.example.movietracker.validator.MovieValidator;
import com.example.movietracker.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final UserRepository userRepository;
    private final MovieMapper movieMapper;
    private final MovieValidator movieValidator;
    private final UserValidator userValidator;

    @Override
    public MovieDto markAsWatched(Long movieId, Long userId) {
        User user = userValidator.getUser(userId);
        Movie movie = movieValidator.getMovie(movieId);
        if (user.getWatched().contains(movie)) {
            throw new ResourceForbiddenException("You have already watched this movie");
        }
        user.getWatched().add(movie);
        userRepository.save(user);
        return movieMapper.toDto(movie);
    }

    @Override
    public MovieDto addToWishlist(Long movieId, Long userId) {
        User user = userValidator.getUser(userId);
        Movie movie = movieValidator.getMovie(movieId);
        if (user.getWishlist().contains(movie)) {
            throw new ResourceForbiddenException("You have already added this movie to wishlist");
        }
        user.getWishlist().add(movie);
        userRepository.save(user);
        return movieMapper.toDto(movie);
    }

    @Override
    public MovieDto removeFromWishlist(Long movieId, Long userId) {
        User user = userValidator.getUser(userId);
        Movie movie = movieValidator.getMovie(movieId);
        if (!user.getWishlist().contains(movie)) {
            throw new ResourceForbiddenException("You haven't added this movie to wishlist");
        }
        user.getWishlist().remove(movie);
        userRepository.save(user);
        return movieMapper.toDto(movie);
    }

}
