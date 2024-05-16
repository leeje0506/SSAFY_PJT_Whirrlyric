package com.example.common.enums;

import com.example.common.converter.AbstractLabelConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum Genre implements LabelEnum{

    KPOP("00"),
    HIPHOP("01"),
    ROCK("02"),
    RAP("03"),
    BALLAD("04"),
    DANCE("05"),
    RNB("06"),
    ACOUSTIC("07"),
    LATIN("08"),
    COUNTRY("09"),
    INDIE("10"),
    TECHNO("11"),
    POP("12"),
    TROT("13"),
    MUSICAL("14"),
    JAZZ("15"),
    EDM("16"),
    DISCO("17"),
    CHILDRENSONG("18"),
    HOUSE("19");

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
