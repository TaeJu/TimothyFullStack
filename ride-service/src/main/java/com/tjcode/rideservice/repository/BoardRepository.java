package com.tjcode.rideservice.repository;

import com.tjcode.rideservice.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
