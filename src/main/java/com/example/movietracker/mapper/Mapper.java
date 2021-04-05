package com.example.movietracker.mapper;

public interface Mapper<T, U> {
    //T toEntity(U dto);
    U toDto(T entity);
}
