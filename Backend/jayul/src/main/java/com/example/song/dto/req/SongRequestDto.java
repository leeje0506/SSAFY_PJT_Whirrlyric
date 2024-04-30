package com.example.song.dto.req;

import com.example.song.config.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRequestDto {

    private String title;
    private Genre genre;
    private String lyrics;
}
