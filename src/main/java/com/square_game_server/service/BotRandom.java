package com.square_game_server.service;

import com.square_game_server.domain.Board;
import com.square_game_server.domain.Side;
import com.square_game_server.domain.SimpleMove;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BotRandom implements BotService {
    @Override
    public SimpleMove doMove(Board board) {
        Side enemySide;
        if (board.getNextPlayerColor() == Side.WHITE) {
            enemySide = Side.BLACK;

        } else {
            enemySide = Side.WHITE;
        }

        for (int y1 = 0; y1 < board.getData().length; y1++) {
            for (int x1 = 0; x1 < board.getData()[y1].length; x1++) {
                for (int lengthOfSquare = 0; y1 + lengthOfSquare < board.getData().length; lengthOfSquare++) {

                    for (int positionOnSquare = 0,
                         x2 = x1 + 1 + lengthOfSquare, //x2 = x1 + 1 + lengthOfSquare - positionOnSquare
                         y2 = y1, // y1 + positionOnSquare
                         x3 = x1 + 1 + lengthOfSquare, //x1 + 1 + lengthOfSquare - positionOnSquare * 2
                         y3 = y1 + 1 + lengthOfSquare,
                         x4 = x1, //x1 - positionOnSquare
                         y4 = y1 + 1 + lengthOfSquare; //y4 = y1 + 1 + lengthOfSquare - positionOnSquare

                         positionOnSquare <= lengthOfSquare
                                 && y2 < board.getData().length
                                 && y3 < board.getData().length
                                 && x3 >= 0
                                 && y4 < board.getData().length
                                 && x4 >= 0;
                         positionOnSquare++,
                                 x2 = x1 + 1 + lengthOfSquare - positionOnSquare,
                                 y2 = y1 + positionOnSquare,
                                 x3 = x1 + 1 + lengthOfSquare - positionOnSquare * 2,
                                 y3 = y1 + 1 + lengthOfSquare,
                                 x4 = x1 - positionOnSquare,
                                 y4 = y1 + 1 + lengthOfSquare - positionOnSquare) {
                        if (x3 >= board.getData().length || x2 >= board.getData().length) {
                            continue;
                        }


                        int countPieces = 0;
                        if (board.getData()[y2][x2].getSide() == enemySide) {
                            countPieces++;
                        }
                        if (board.getData()[y3][x3].getSide() == enemySide) {
                            countPieces++;
                        }
                        if (board.getData()[y4][x4].getSide() == enemySide) {
                            countPieces++;
                        }
                        if (board.getData()[y1][x1].getSide() == enemySide) {
                            countPieces++;
                        }
                        if (countPieces == 3) {
                            if (board.getData()[y1][x1].getSide() == null) {
                                return new SimpleMove(x1, y1, board.getNextPlayerColor());
                            }
                            if (board.getData()[y2][x2].getSide() == null) {
                                return new SimpleMove(x2, y2, board.getNextPlayerColor());
                            }
                            if (board.getData()[y3][x3].getSide() == null) {
                                return new SimpleMove(x3, y3, board.getNextPlayerColor());
                            }
                            if (board.getData()[y4][x4].getSide() == null) {
                                return new SimpleMove(x4, y4, board.getNextPlayerColor());
                            }
                        }

                        countPieces = 0;
                        if (board.getData()[y1][x1].getSide() == board.getNextPlayerColor()) {
                            countPieces++;
                        }
                        if (board.getData()[y2][x2].getSide() == board.getNextPlayerColor()) {
                            countPieces++;
                        }
                        if (board.getData()[y3][x3].getSide() == board.getNextPlayerColor()) {
                            countPieces++;
                        }
                        if (board.getData()[y4][x4].getSide() == board.getNextPlayerColor()) {
                            countPieces++;
                        }
                        if (countPieces == 3) {
                            if (board.getData()[y1][x1].getSide() == null) {
                                return new SimpleMove(x1, y1, board.getNextPlayerColor());
                            }
                            if (board.getData()[y2][x2].getSide() == null) {
                                return new SimpleMove(x2, y2, board.getNextPlayerColor());
                            }
                            if (board.getData()[y3][x3].getSide() == null) {
                                return new SimpleMove(x3, y3, board.getNextPlayerColor());
                            }
                            if (board.getData()[y4][x4].getSide() == null) {
                                return new SimpleMove(x4, y4, board.getNextPlayerColor());
                            }
                        }
                    }
                }
            }
        }
        int row, col;
        Random rand = new Random();

        do {
            row = rand.nextInt(board.getData().length);
            col = rand.nextInt(board.getData()[row].length);
        } while (board.getData()[col][row].getSide() != null);
        return new SimpleMove(row, col, board.getNextPlayerColor());
    }
}
