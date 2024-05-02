package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.dto.res.MypageProfileResponse;
import com.example.member.repository.MemberCustomRepository;
import com.example.song.domain.Mainsong;
import com.example.song.domain.Song;
import com.example.song.dto.res.SongResponse;
import com.example.song.repository.MainsongCustomRepository;
import com.example.song.repository.SongCustomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService {
    private final MemberCustomRepository memberRepository;
    private final MainsongCustomRepository mainSongRepository;
    private final SongCustomRepository songRepository;

    public MypageProfileResponse getMemberProfile(int memberId) {
        Member member = memberRepository.findMemberById(memberId);

        Mainsong mainSong = mainSongRepository.findMainsongByMemberId(memberId);

        SongResponse mainsongResponse = SongResponse
            .builder()
            .nickname(mainSong.getMember().getNickname())
            .song(mainSong.getSong())
            .build();

        List<Song> songList = songRepository.getSongList(memberId);

        List<SongResponse> songResponseList = songList.stream()
            .map(song -> SongResponse.builder()
                .song(song)
                .nickname(song.getMember() != null ? song.getMember().getNickname() : null)
                .build())
            .toList();

        return MypageProfileResponse.builder()
            .nickname(member.getNickname())
            .imageUrl(member.getProfileImage())
            .mainSong(mainsongResponse)
            .songList(songResponseList)
            .build();
    }
}
