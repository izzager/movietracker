package com.example.movietracker.validator;

public interface DtoValidator<T> {
    void validate(T dto);
}
