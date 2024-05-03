package com.example.song.dto.res;

import com.example.song.domain.Song;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class SongResponse {
    private Song song;
    private String nickname;
}
