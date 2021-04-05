package com.example.movietracker.service;

import com.example.movietracker.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto addReview(ReviewDto reviewDto);
    List<ReviewDto> getReviewsForMovie(Long movieId);
}
