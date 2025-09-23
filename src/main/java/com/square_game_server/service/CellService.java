package com.square_game_server.service;

import com.square_game_server.domain.Cell;
import com.square_game_server.domain.SimpleMove;

public interface CellService {
    String calculateWinner(Cell[][] board);
    void setSideOnBoard(Cell[][] board, SimpleMove simpleMove);

}
