package com.square_game_server.service;

import com.square_game_server.domain.Cell;
import com.square_game_server.domain.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Класс CellServiceImplTest")
@ExtendWith(MockitoExtension.class)
public class CellServiceImplTest {
    private CellServiceImpl cellService;

    @DisplayName("должен выявить победу")
    @Test
    void  shouldCheckWinner(){
        CellServiceImpl cellServiceImpl = new CellServiceImpl();
        Cell[][] board = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Cell();
            }
        }
        board[0][2].setSide(Side.WHITE);
        board[4][2].setSide(Side.WHITE);
        board[2][0].setSide(Side.WHITE);
        board[2][4].setSide(Side.WHITE);
        String string = cellServiceImpl.calculateWinner(board);
        String answer = "w "+"("+ 2 +"; "+ 0 + "), " +
                "("+ 4 +"; "+ 2 + "), ("+ 2 +"; "+ 4 + "), ("+ 0 +"; "+ 2 +")";
        assertThat(string).isEqualTo(answer);

    }

}
