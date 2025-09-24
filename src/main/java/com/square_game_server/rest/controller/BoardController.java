package com.square_game_server.rest.controller;


import com.square_game_server.domain.Board;
import com.square_game_server.rest.Dto.BoardDto;
import com.square_game_server.service.CellService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BoardController {
    private final CellService cellService;

    @PostMapping("/api/board/1/checkWin")
    private String checkWinner(@RequestBody BoardDto boardDto) {
        Board board = BoardDto.boardDtoToDomainObject(boardDto);
        return cellService.calculateWinner(board.getData());
    }
}
