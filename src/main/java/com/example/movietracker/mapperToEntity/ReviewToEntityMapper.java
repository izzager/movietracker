package com.example.movietracker.mapperToEntity;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Review;
import com.example.movietracker.helper.MovieRepositoryHelper;
import com.example.movietracker.helper.UserRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewToEntityMapper implements MapperToEntity<ReviewDto, Review> {

    private final UserRepositoryHelper userRepositoryHelper;
    private final MovieRepositoryHelper movieRepositoryHelper;
    private final ModelMapper modelMapper;

    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();
        modelMapper.map(reviewDto, review);
        review.setUser(userRepositoryHelper.ensureUserExists(reviewDto.getUserId()));
        review.setMovie(movieRepositoryHelper.ensureMovieExists(reviewDto.getMovieId()));
        return review;
    }

}
