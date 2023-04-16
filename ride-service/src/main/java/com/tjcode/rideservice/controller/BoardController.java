package com.tjcode.rideservice.controller;

import com.tjcode.rideservice.dto.BoardDTO;
import com.tjcode.rideservice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getBoard")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<BoardDTO>> getBoard(@RequestParam(defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok(boardService.findAll(pageNumber));
    }

    @GetMapping("/getBoard/{boardId}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok(boardService.findById(boardId));
    }
}
