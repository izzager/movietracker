package com.example.movietracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;

    private Long userId;

    private Long movieId;

    private String comment;

    private int rating;

}
