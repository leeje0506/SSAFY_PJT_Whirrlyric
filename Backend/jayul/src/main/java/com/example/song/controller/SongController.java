package com.example.song.controller;

import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

}
