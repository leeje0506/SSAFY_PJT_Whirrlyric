package com.example.song.dto.res;

import com.example.common.enums.Genre;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class GenreList {
    private Genre genre;
    private List<SongResponse> songList;
}
