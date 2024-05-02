package com.example.member.controller;

import com.example.member.dto.res.MypageProfileResponse;
import com.example.member.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mypage")
@RequiredArgsConstructor
public class MypageController {
    private final MypageService myPageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberProfile(@PathVariable int id){
        System.out.println("마이페이지 GET");
        MypageProfileResponse response = myPageService.getMemberProfile(id);
        return ResponseEntity.ok(response);
    }
}
