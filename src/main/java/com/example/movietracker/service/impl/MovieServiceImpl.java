package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.NotFoundException;
import com.example.movietracker.exception.ResourceForbiddenException;
import com.example.movietracker.mapper.MovieMapper;
import com.example.movietracker.repository.MovieRepository;
import com.example.movietracker.repository.UserRepository;
import com.example.movietracker.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieDto markAsWatched(Long movieId, Long userId) {
        User user = getUser(userId);
        Movie movie = getMovie(movieId);
        if (user.getWatched().contains(movie)) {
            throw new ResourceForbiddenException("You have already watched this movie");
        }
        user.getWatched().add(movie);
        userRepository.save(user);
        return movieMapper.toDto(movie);
    }

    @Override
    public MovieDto addToWishlist(Long movieId, Long userId) {
        User user = getUser(userId);
        Movie movie = getMovie(movieId);
        if (user.getWishlist().contains(movie)) {
            throw new ResourceForbiddenException("You have already added this movie to wishlist");
        }
        user.getWishlist().add(movie);
        userRepository.save(user);
        return movieMapper.toDto(movie);
    }

    @Override
    public MovieDto removeFromWishlist(Long movieId, Long userId) {
        User user = getUser(userId);
        Movie movie = getMovie(movieId);
        if (!user.getWishlist().contains(movie)) {
            throw new ResourceForbiddenException("You haven't added this movie to wishlist");
        }
        user.getWishlist().remove(movie);
        userRepository.save(user);
        return movieMapper.toDto(movie);
    }

    private User getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userOptional.get();
    }

    private Movie getMovie(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new NotFoundException("Movie not found");
        }
        return movieOptional.get();
    }
}
