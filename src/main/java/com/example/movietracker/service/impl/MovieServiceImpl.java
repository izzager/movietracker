package com.example.movietracker.service.impl;

import com.example.movietracker.repository.MovieRepository;
import com.example.movietracker.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

}
