package com.example.movietracker.service.impl;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.repository.ReviewRepository;
import com.example.movietracker.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public List<ReviewDto> getReviewsForMovie(Long movieId) {
        return null;
    }
}
