package com.example.movietracker.mapperToEntity;

public interface MapperToEntity<D, E> {
    E toEntity(D dto);
}
