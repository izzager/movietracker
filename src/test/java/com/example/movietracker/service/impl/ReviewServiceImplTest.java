package com.example.movietracker.service.impl;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.Review;
import com.example.movietracker.helper.MovieRepositoryHelper;
import com.example.movietracker.mapperToEntity.ReviewToEntityMapper;
import com.example.movietracker.repository.ReviewRepository;
import com.example.movietracker.validator.ReviewDtoValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static com.example.movietracker.TestConstants.MOVIE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    @InjectMocks
    ReviewServiceImpl reviewService;

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ReviewToEntityMapper reviewToEntityMapper;

    @Mock
    MovieRepositoryHelper movieRepositoryHelper;

    @Mock
    ReviewDtoValidator reviewDtoValidator;


    @Test
    public void addReview_ok() {
        ReviewDto reviewDto = new ReviewDto();
        Review review = new Review();

        when(reviewToEntityMapper.toEntity(reviewDto)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);
        reviewService.addReview(reviewDto);

        verify(reviewDtoValidator).validate(reviewDto);
        verify(reviewToEntityMapper).toEntity(reviewDto);
        verify(reviewRepository).save(review);
        verify(modelMapper).map(review, ReviewDto.class);
    }

    @Test
    public void getReviewsForMovie_ok() {
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        Review review = new Review();
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        movie.setReviews(reviewList);

        when(movieRepositoryHelper.ensureMovieExists(MOVIE_ID)).thenReturn(movie);
        List<ReviewDto> result = reviewService.getReviewsForMovie(MOVIE_ID);

        assertEquals(result.size(), reviewList.size());
        verify(movieRepositoryHelper).ensureMovieExists(MOVIE_ID);
        verify(modelMapper).map(review, ReviewDto.class);
    }

}