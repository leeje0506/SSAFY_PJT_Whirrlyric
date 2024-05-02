package com.example.song.repository;

import com.example.song.domain.Mainsong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainsongRepository extends JpaRepository<Mainsong, Integer> {

}
