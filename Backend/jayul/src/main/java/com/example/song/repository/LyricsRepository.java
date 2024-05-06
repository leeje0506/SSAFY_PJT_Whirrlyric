package com.example.song.repository;

import com.example.song.domain.Lyrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LyricsRepository extends JpaRepository<Lyrics, Long> {
    // 필요한 커스텀 메소드 추가 가능
}
