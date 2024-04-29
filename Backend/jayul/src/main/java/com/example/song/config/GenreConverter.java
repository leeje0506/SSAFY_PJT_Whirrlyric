package com.example.song.config;

import com.example.song.config.Genre;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// JPA Converter 작성
@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {
    @Override
    public String convertToDatabaseColumn(Genre genre) {
        return genre != null ? genre.getCode() : null;
    }

    @Override
    public Genre convertToEntityAttribute(String s) {
        return null;
    }

}
