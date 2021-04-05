package com.example.movietracker.service.impl;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.Review;
import com.example.movietracker.mapper.ReviewMapper;
import com.example.movietracker.repository.ReviewRepository;
import com.example.movietracker.service.ReviewService;
import com.example.movietracker.validator.MovieValidator;
import com.example.movietracker.validator.ReviewDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final MovieValidator movieValidator;
    private final ReviewDtoValidator reviewDtoValidator;

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        reviewDtoValidator.validate(reviewDto);
        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDto> getReviewsForMovie(Long movieId) {
        return movieValidator.getMovie(movieId).getReview()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

}
