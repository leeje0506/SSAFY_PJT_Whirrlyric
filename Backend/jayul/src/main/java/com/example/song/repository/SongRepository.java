package com.example.song.repository;

import com.example.song.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
    // 필요한 커스텀 메소드 추가 가능
}

