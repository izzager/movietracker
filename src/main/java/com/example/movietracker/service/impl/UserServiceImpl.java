package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.ResourceNotFoundException;
import com.example.movietracker.helper.UserRepositoryHelper;
import com.example.movietracker.repository.UserRepository;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepositoryHelper userRepositoryHelper;
    private final UserRepository userRepository;

    @Override
    public MovieDto findNextMovieInWishlist(Long userId) {
        User user = userRepositoryHelper.ensureUserExists(userId);
        if (user.getWishlist().isEmpty()) {
            throw new ResourceNotFoundException("Your wishlist is empty");
        }
        return modelMapper.map(user.getWishlist().get(0), MovieDto.class);
    }

    @Override
    public List<MovieDto> getWatchedMovies(Long userId) {
        return userRepositoryHelper.ensureUserExists(userId).getWatched()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getWishList(Long userId) {
        return userRepositoryHelper.ensureUserExists(userId).getWishlist()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
