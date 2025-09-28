package com.square_game_server.service;

import com.square_game_server.config.BotPriorityConfig;
import com.square_game_server.domain.Board;
import com.square_game_server.domain.Cell;
import com.square_game_server.domain.Side;
import com.square_game_server.domain.SimpleMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Класс BotServicePriority")
@ExtendWith(MockitoExtension.class)
public class BotServicePriorityTest {
    private BotServicePriority botServicePriority;

    @Test
    @DisplayName("Должен защищаться")
    void shouldDefend(){
        Cell[][] cells = new Cell[8][8];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell();}

        }
        botServicePriority = new BotServicePriority(new BotPriorityConfig());
        cells[0][0].setSide(Side.WHITE);
        cells[0][1].setSide(Side.WHITE);
        cells[1][0].setSide(Side.WHITE);
        cells[2][0].setSide(Side.BLACK);
        cells[2][1].setSide(Side.BLACK);
        Board board = new Board(cells,Side.BLACK);
        SimpleMove simpleMoveAnswer = new SimpleMove(1,1,board.getNextPlayerColor());
        SimpleMove simpleMove = botServicePriority.doMove(board);
        assertThat(simpleMoveAnswer).isEqualTo(simpleMove);



    }

}
