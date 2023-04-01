package com.tjcode.rideservice.service;

import com.tjcode.rideservice.dto.BoardDTO;
import com.tjcode.rideservice.entity.BoardEntity;
import com.tjcode.rideservice.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardDTO save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
        return boardDTO;
    }
}
