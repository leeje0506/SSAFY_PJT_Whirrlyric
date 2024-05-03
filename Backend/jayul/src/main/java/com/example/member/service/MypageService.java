package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.dto.res.MypageProfileResponse;
import com.example.member.repository.MemberCustomRepository;
import com.example.song.domain.Mainsong;
import com.example.song.domain.Song;
import com.example.song.dto.res.SongResponse;
import com.example.song.repository.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService {
    private final MemberCustomRepository memberRepository;
    private final MainsongCustomRepository mainSongCustomRepository;
    private final SongCustomRepository songRepository;
    private final MainsongRepository mainsongRepository;

    public MypageProfileResponse getMemberProfile(int memberId) {
        Member member = memberRepository.findMemberById(memberId);

        Mainsong mainSong = mainSongCustomRepository.findMainsongByMemberId(memberId);

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

    public Integer setMainSong(int songId, String nickname) {
        Song song = songRepository.findSongBySongId(songId);

        Member member = memberRepository.findMemberByNickname(nickname);

        Mainsong existedMainsong = mainSongCustomRepository.findExistedMainsong(member);

        Mainsong savedSong;

        if(existedMainsong == null){    // 대표곡을 처음 설정할 때
            Mainsong mainsong = Mainsong.builder()
                .song(song)
                .member(member)
                .build();
            savedSong = mainsongRepository.save(mainsong);
        } else { // 대표곡을 수정할 때
            existedMainsong.setSong(song);
            savedSong = mainsongRepository.save(existedMainsong);
        }

        return savedSong.getId();
    }
}
