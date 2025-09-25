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
        if(board.getNextPlayerColor() == Side.WHITE) {
            enemySide = Side.BLACK;

        }else {
            enemySide = Side.WHITE;
        }

        for (int y1 = 0; y1 < board.getData().length; y1++) {
            for (int x1 = 0; x1 < board.getData()[y1].length; x1++) {

                for (int lengthOfCube = 0; y1 + lengthOfCube < board.getData().length
                        && x1 + lengthOfCube < board.getData().length; lengthOfCube++) {
                    int positionOnCube = 0;
                    int x2 = x1 + 1 + lengthOfCube - positionOnCube, y2 = y1 + positionOnCube;
                    int x3 = x1 + 1 + lengthOfCube - positionOnCube * 2, y3 = y1 + 1 + lengthOfCube;
                    int x4 = x1 - positionOnCube, y4 = y1 + 1 + lengthOfCube - positionOnCube;


                    for (; positionOnCube <= lengthOfCube
                            && y2 < board.getData().length
                            && x2 < board.getData().length
                            && y3 < board.getData().length
                            && x3 < board.getData().length
                            && x3 >= 0
                            && y4 < board.getData().length
                            && x4 >= 0;
                         positionOnCube++,
                                 x2 = x1 + 1 + lengthOfCube - positionOnCube,
                                 y2 = y1 + positionOnCube,
                                 x3 = x1 + 1 + lengthOfCube - positionOnCube * 2,
                                 y3 = y1 + 1 + lengthOfCube,
                                 x4 = x1 - positionOnCube,
                                 y4 = y1 + 1 + lengthOfCube - positionOnCube) {


                        int countPieces = 0;
                        if (board.getData()[y2][x2].getSide() == enemySide) {
                            countPieces++;
                        }
                        if (board.getData()[y3][x3].getSide() == enemySide){
                            countPieces++;
                        }
                        if (board.getData()[y4][x4].getSide() == enemySide) {
                            countPieces++;
                        }
                        if(board.getData()[y1][x1].getSide() == enemySide){
                            countPieces++;
                        }
                        if(countPieces == 3) {
                            if(board.getData()[y1][x1].getSide() == null){
                                return new SimpleMove(x1,y1,board.getNextPlayerColor());
                            }
                            if(board.getData()[y2][x2].getSide() == null){
                                return new SimpleMove(x2,y2,board.getNextPlayerColor());
                            }
                            if(board.getData()[y3][x3].getSide() == null){
                                return new SimpleMove(x3,y3,board.getNextPlayerColor());
                            }
                            if(board.getData()[y4][x4].getSide() == null){
                                return new SimpleMove(x4,y4,board.getNextPlayerColor());
                            }
                        }

                    }

                    }

                }

            }
        int row, col;

        do {
            Random rand = new Random();
            row = rand.nextInt(board.getData().length);
            col = rand.nextInt(board.getData()[row].length);
        } while (board.getData()[col][row].getSide() != null);
        return new SimpleMove(row,col,board.getNextPlayerColor());
    }
}
