package com.tjcode.rideservice.controller;

import com.tjcode.rideservice.dto.BoardDTO;
import com.tjcode.rideservice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board-service")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/uploadBoard")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<BoardDTO> uploadBoard(@RequestBody BoardDTO boardDTO) {
        return ResponseEntity.ok(boardService.save(boardDTO));
    }
}
