package com.example.movietracker.mapperToEntity;

public interface MapperToEntity<T, U> {
    U toEntity(T dto);
}
