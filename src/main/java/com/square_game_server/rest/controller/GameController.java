package com.square_game_server.rest.controller;

import com.square_game_server.domain.Board;
import com.square_game_server.domain.SimpleMove;
import com.square_game_server.rest.Dto.BoardDto;
import com.square_game_server.rest.Dto.SimpleMoveDto;
import com.square_game_server.service.BotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class GameController {
    private final BotService botService;
    @PostMapping("/api/{rules}/nextMove")
    public SimpleMoveDto nextMove(@PathVariable("rules") String rules, @RequestBody BoardDto boardDto) {
        Board board = BoardDto.boardDtoToDomainObject(boardDto);
        SimpleMove simpleMove = botService.doMove(board);
        return SimpleMoveDto.domainObjectToDto(simpleMove);
    }

}
