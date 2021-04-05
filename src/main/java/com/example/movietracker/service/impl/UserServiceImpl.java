package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.NotFoundException;
import com.example.movietracker.mapper.MovieMapper;
import com.example.movietracker.service.UserService;
import com.example.movietracker.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MovieMapper movieMapper;
    private final UserValidator userValidator;

    @Override
    public MovieDto findNextMovieInWishlist(Long userId) {
        User user = userValidator.getUser(userId);
        Movie movie = user.getWishlist().get(0);
        if (movie == null) {
            throw new NotFoundException("Your wishlist is empty");
        }
        return movieMapper.toDto(movie);
    }

    @Override
    public List<MovieDto> getWatchedMovies(Long userId) {
        return userValidator.getUser(userId).getWatched()
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getWishList(Long userId) {
        return userValidator.getUser(userId).getWishlist()
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

}
