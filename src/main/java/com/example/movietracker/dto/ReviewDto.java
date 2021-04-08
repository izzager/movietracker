package com.example.movietracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    @Null(message = "Review ID must be null")
    private Long id;

    private Long userId;

    @NotNull(message = "Movie ID must not be null")
    private Long movieId;

    private String comment;

    @Min(value = 1, message = "Rating value is outside of the valid range [1; 10]")
    @Max(value = 10, message = "Rating value is outside of the valid range [1; 10]")
    private int rating;

    @Null(message = "Review date must be null")
    private LocalDateTime date;

}
