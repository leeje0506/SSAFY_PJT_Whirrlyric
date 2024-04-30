package com.example.song.dto.res;

import com.example.song.config.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongResponseDto {

    private Integer songId;
    private String title;
    private Genre genre;
    private String lyrics;
    private String imageUrl;
    private String songUrl;
}
