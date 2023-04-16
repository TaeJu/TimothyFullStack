package com.tjcode.rideservice.service;

import com.tjcode.rideservice.dto.BoardDTO;
import com.tjcode.rideservice.entity.BoardEntity;
import com.tjcode.rideservice.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardDTO save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
        return boardDTO;
    }

    public List<BoardDTO> findAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,8);
        Page<BoardEntity> boardEntityList = boardRepository.findAll(pageable);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }

    public BoardDTO findById(Long boardId) {
        return BoardDTO.toBoardDTO(boardRepository.findById(boardId).get());
    }
}
