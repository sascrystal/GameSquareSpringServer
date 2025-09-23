package com.square_game_server.service;

import com.square_game_server.domain.Board;
import com.square_game_server.domain.SimpleMove;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class BotRandom implements BotService {
    @Override
    public SimpleMove doMove(Board board) {
        int row, col;

        do {
            Random rand = new Random();
            row = rand.nextInt(board.getData().length);
            col = rand.nextInt(board.getData()[row].length);
        } while (board.getData()[col][row].getSide() != null);
        return new SimpleMove(row,col,board.getNextPlayerColor());
    }
}
