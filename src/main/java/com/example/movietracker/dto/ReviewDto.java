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

    @Null(message = "Incorrect format of review")
    private Long id;

    private Long userId;

    @NotNull(message = "Movie not found")
    private Long movieId;

    private String comment;

    @Min(value = 1, message = "Incorrect format of review")
    @Max(value = 10, message = "Incorrect format of review")
    private int rating;

    @Null(message = "Incorrect format of review")
    private LocalDateTime date;

}
