package com.example.movietracker.converterToDto;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Review;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ReviewConverter implements Converter<Review, ReviewDto>{

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ReviewDto convert(MappingContext<Review, ReviewDto> mappingContext) {
        Review entity = mappingContext.getSource();
        ReviewDto reviewDto = new ReviewDto();
        modelMapper.map(entity, reviewDto);
        reviewDto.setMovieId(entity.getMovie().getId());
        reviewDto.setUserId(entity.getUser().getId());
        reviewDto.setDate(entity.getCreationDate());
        return reviewDto;
    }

}
