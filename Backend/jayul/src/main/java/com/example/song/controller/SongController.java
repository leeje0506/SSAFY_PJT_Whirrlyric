package com.example.song.controller;

import com.example.song.dto.res.SongResponse;
import com.example.song.service.SongService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSongDetail(@PathVariable int id){
        SongResponse response = songService.getSongDetail(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getFilteredList(@RequestParam String genre){

        List<SongResponse> response = songService.findSongsByGenre(genre);

        return ResponseEntity.ok(response);
    }
}
