package com.example.song.service;

import com.example.song.repository.MainsongRepository;
import com.example.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;
    private final MainsongRepository mainsongRepository;

}
