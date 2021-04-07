package com.example.movietracker.service.impl;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Review;
import com.example.movietracker.helper.MovieRepositoryHelper;
import com.example.movietracker.mapperToEntity.ReviewToEntityMapper;
import com.example.movietracker.repository.ReviewRepository;
import com.example.movietracker.service.ReviewService;
import com.example.movietracker.validator.ReviewDtoValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final ReviewToEntityMapper reviewToEntityMapper;
    private final MovieRepositoryHelper movieRepositoryHelper;
    private final ReviewDtoValidator reviewDtoValidator;

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        reviewDtoValidator.validate(reviewDto);
        Review review = reviewToEntityMapper.toEntity(reviewDto);
        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getReviewsForMovie(Long movieId) {
        return movieRepositoryHelper.ensureMovieExists(movieId).getReviews()
                .stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

}
