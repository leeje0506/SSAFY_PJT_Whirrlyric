package com.example.member.controller;

import com.example.member.domain.Member;
import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.req.MainSongRequest;
import com.example.member.dto.res.MemberEditDto;
import com.example.member.dto.res.MypageProfileResponse;
import com.example.member.service.MypageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mypage")
@RequiredArgsConstructor
public class MypageController {
    private final MypageService myPageService;

    @Operation(
        summary = "닉네임 변경",
        description = "본인의 닉네임을 변경한다. (특수 문자 및 공백 제외 20글자 이내)"
    )
    @PostMapping("/rename")
    public ResponseEntity<MemberEditDto> editNickname(
        @AuthenticationPrincipal Member member,
        @Valid @RequestBody MemberEditDto request
    ){
        return ResponseEntity.ok(myPageService.updateMember(member,request));
    }

    @Operation(
        summary = "회원탈퇴",
        description = "회원을 비활성화시킨다. (soft deletion)"
    )
    @DeleteMapping("/withdraw/{oauthServerType}")
    public ResponseEntity<Void> removeMember(
        @AuthenticationPrincipal Member member,
        HttpServletResponse response,
        @PathVariable OauthServerType oauthServerType
    ){
        myPageService.deleteMember(response,oauthServerType,member);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberProfile(@PathVariable int id){
        System.out.println("마이페이지 GET");
        MypageProfileResponse response = myPageService.getMemberProfile(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mainsong")
    public ResponseEntity<?> setMainSong(@RequestBody MainSongRequest mainSongRequest){
        Integer createdId = myPageService.setMainSong(mainSongRequest.getSongId(), mainSongRequest.getNickname());

        return ResponseEntity.ok(createdId);
    }
}
