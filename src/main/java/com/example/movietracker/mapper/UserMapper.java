package com.example.movietracker.mapper;

import com.example.movietracker.dto.UserDto;
import com.example.movietracker.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    private final MovieMapper movieMapper;

    @Override
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setUsername(entity.getUsername());
        userDto.setWishlist(entity.getWishlist()
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toSet()));
        userDto.setWatched(entity.getWatched()
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toSet()));
        return userDto;
    }

}
