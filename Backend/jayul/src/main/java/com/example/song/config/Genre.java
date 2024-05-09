package com.example.song.config;


// Java에서 열거형 정의
public enum Genre {
    KPOP("01"),
    HIPHOP("02"),
    BALLAD("03"),
    RAP("04"),
    MUSICAL("05"),
    All("99");


    private final String code;

    Genre(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Genre fromCode(String code) {
        for (Genre genre : Genre.values()) {
            if (genre.getCode().equalsIgnoreCase(code)) { // 대소문자를 구분하지 않고 비교
                return genre;
            }
        }
        throw new IllegalArgumentException("Unknown genre code: " + code);
    }

    public static Genre fromName(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.name().equalsIgnoreCase(name)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Unknown genre name: " + name);
    }

}


