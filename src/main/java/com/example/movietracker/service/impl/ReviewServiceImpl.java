package com.example.movietracker.service.impl;

import com.example.movietracker.repository.ReviewRepository;
import com.example.movietracker.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

}
