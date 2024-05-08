package com.example.song.dto.res;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class SongListResponse {
    private List<SongResponse> popularSongList;
    private List<SongResponse> latestSongList;
    private List<GenreList> genreSongList;
}
