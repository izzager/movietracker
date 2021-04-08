package com.example.movietracker.controller;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.User;
import com.example.movietracker.security.UserContext;
import com.example.movietracker.service.ReviewService;
import com.example.movietracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static com.example.movietracker.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @InjectMocks
    ReviewController reviewController;

    @Mock
    ReviewService reviewService;

    @Mock
    UserService userService;

    @Mock
    UserContext userContext;

    @Test
    public void addReview_ok() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        Authentication authentication = Mockito.mock(Authentication.class);
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUserId(USER_ID);
        reviewDto.setMovieId(MOVIE_ID);
        reviewDto.setComment(REVIEW_COMMENT);
        reviewDto.setRating(REVIEW_RATING);

        when(userContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USERNAME);
        when(userService.findByUsername(USERNAME)).thenReturn(user);
        when(reviewService.addReview(reviewDto)).thenReturn(reviewDto);
        ReviewDto result = reviewController.addReview(reviewDto);

        assertEquals(result.getMovieId(), MOVIE_ID);
        assertEquals(result.getUserId(), USER_ID);
        assertEquals(result.getComment(), REVIEW_COMMENT);
        assertEquals(result.getRating(), REVIEW_RATING);
        verify(userContext).getAuthentication();
        verify(userService).findByUsername(USERNAME);
    }

    @Test
    public void getMovieReviews() {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setMovieId(MOVIE_ID);
        reviewDtoList.add(reviewDto);

        Mockito.when(reviewService.getReviewsForMovie(MOVIE_ID)).thenReturn(reviewDtoList);
        List<ReviewDto> result = reviewController.getMovieReviews(MOVIE_ID);

        assertEquals(result.size(), reviewDtoList.size());
        assertEquals(result.get(0).getMovieId(), MOVIE_ID);
        verify(reviewService).getReviewsForMovie(MOVIE_ID);
    }

}