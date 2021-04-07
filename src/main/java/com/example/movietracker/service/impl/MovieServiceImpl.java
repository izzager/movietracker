package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.ResourceForbiddenException;
import com.example.movietracker.helper.MovieRepositoryHelper;
import com.example.movietracker.helper.UserRepositoryHelper;
import com.example.movietracker.repository.UserRepository;
import com.example.movietracker.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MovieRepositoryHelper movieRepositoryHelper;
    private final UserRepositoryHelper userRepositoryHelper;

    @Override
    public MovieDto markAsWatched(Long movieId, Long userId) {
        User user = userRepositoryHelper.ensureUserExists(userId);
        Movie movie = movieRepositoryHelper.ensureMovieExists(movieId);
        if (user.getWatched().contains(movie)) {
            throw new ResourceForbiddenException("You have already watched this movie");
        }
        user.getWatched().add(movie);
        userRepository.save(user);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto addToWishlist(Long movieId, Long userId) {
        User user = userRepositoryHelper.ensureUserExists(userId);
        Movie movie = movieRepositoryHelper.ensureMovieExists(movieId);
        if (user.getWishlist().contains(movie)) {
            throw new ResourceForbiddenException("You have already added this movie to wishlist");
        }
        user.getWishlist().add(movie);
        userRepository.save(user);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto removeFromWishlist(Long movieId, Long userId) {
        User user = userRepositoryHelper.ensureUserExists(userId);
        Movie movie = movieRepositoryHelper.ensureMovieExists(movieId);
        if (!user.getWishlist().contains(movie)) {
            throw new ResourceForbiddenException("You haven't added this movie to wishlist");
        }
        user.getWishlist().remove(movie);
        userRepository.save(user);
        return modelMapper.map(movie, MovieDto.class);
    }

}
