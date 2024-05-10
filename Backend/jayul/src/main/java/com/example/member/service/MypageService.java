package com.example.member.service;

import com.example.common.exception.BaseException;
import com.example.common.exception.ErrorCode;
import com.example.member.domain.Member;
import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.res.MemberEditDto;
import com.example.member.dto.res.MypageProfileResponse;
import com.example.member.repository.MemberCustomRepository;
import com.example.member.repository.MemberRepository;
import com.example.song.domain.Mainsong;
import com.example.song.domain.Song;
import com.example.song.dto.res.SongResponse;
import com.example.song.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService {

    private final MemberCustomRepository memberCustomRepository;
    private final MainsongCustomRepository mainSongCustomRepository;
    private final SongCustomRepository songRepository;
    private final MainsongRepository mainsongRepository;
    private final MemberRepository memberRepository;

    private final OauthService oauthService;
    private final TokenService tokenService;

    public MypageProfileResponse getMemberProfile(int memberId) {
        Member member = memberCustomRepository.findMemberById(memberId);

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

        Member member = memberCustomRepository.findMemberByNickname(nickname);

        Mainsong existedMainsong = mainSongCustomRepository.findExistedMainsong(member);

        Mainsong savedSong;

        if (existedMainsong == null) {    // 대표곡을 처음 설정할 때
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

    public MemberEditDto updateMember(Member member, MemberEditDto request) {
        Optional<Member> existingMember = memberRepository.findByNickname(request.nickname());
        if (existingMember.isPresent()) {
            throw new BaseException(ErrorCode.MEMBER_NICKNAME_DUPLICATED);
        }

        member.setNickname(request.nickname());
        memberRepository.save(member);

        return MemberEditDto.builder().nickname(request.nickname()).build();
    }

    @Operation(
        summary = "회원가입 탈퇴"
    )
    public void deleteMember(HttpServletResponse response, OauthServerType oauthServerType,
        Member member) {
        oauthService.logout(response, oauthServerType,
            member.getOauthId().getOauthServerId());   //카카오톡 로그아웃
        tokenService.deleteHeader(response);
        member.setIsDeleted(true);
        memberRepository.save(member);
    }
}
