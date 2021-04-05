package com.example.movietracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;

    private Long userId;

    private Long movieId;

    private String comment;

    private int rating;

    private LocalDateTime date;

}
