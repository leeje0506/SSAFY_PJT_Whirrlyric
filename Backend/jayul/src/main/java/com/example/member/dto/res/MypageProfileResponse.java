package com.example.member.dto.res;

import com.example.song.dto.res.SongResponse;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class MypageProfileResponse {
    private String nickname;
    private String imageUrl;
    private SongResponse mainSong;
    private List<SongResponse> songList;
}
