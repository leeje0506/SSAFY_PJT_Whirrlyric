package com.example.common.enums;

import com.example.common.converter.AbstractLabelConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum Genre implements LabelEnum{

    KPOP("00"), HIPHOP("01"), ROCK("02"), RAP("03");

    private final String label;

    Genre(String label){
        this.label = label;
    }

    @Converter(autoApply = true)
    static class ConverterAbstract extends AbstractLabelConverter<Genre>{
        public ConverterAbstract() {
            super(Genre.class);
        }
    }
}
