package com.square_game_server.service;

import com.square_game_server.domain.Cell;
import com.square_game_server.domain.Side;
import com.square_game_server.domain.SimpleMove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CellServiceImpl implements CellService {
    @Override
    public String calculateWinner(Cell[][] board) {
        boolean isDraw = true;
        for (int y1 = 0; y1 < board.length; y1++) {
            for (int x1 = 0; x1 < board[y1].length; x1++) {
                Side side = board[y1][x1].getSide();
                if (side == null) {
                    isDraw = false;
                }
                for (int lengthOfCube = 0; side != null
                        && y1 + lengthOfCube < board.length
                        && x1 + lengthOfCube < board.length; lengthOfCube++) {
                    int positionOnCube = 0;
                    int x2 = x1 + 1 + lengthOfCube - positionOnCube, y2 = y1 + positionOnCube;
                    int x3 = x1 + 1 + lengthOfCube - positionOnCube * 2, y3 = y1 + 1 + lengthOfCube;
                    int x4 = x1 - positionOnCube, y4 = y1 + 1 + lengthOfCube - positionOnCube;



                    for (;
                         positionOnCube <= lengthOfCube
                            && y2 < board.length
                            && x2 < board.length
                            && y3 < board.length
                            && x3 < board.length
                            && x3 >= 0
                            && y4 < board.length
                            && x4 >= 0;
                         positionOnCube++,
                                 x2 = x1 + 1 + lengthOfCube - positionOnCube,
                                 y2 = y1 + positionOnCube,
                                 x3 = x1 + 1 + lengthOfCube - positionOnCube * 2,
                                 y3 = y1 + 1 + lengthOfCube,
                                 x4 = x1 - positionOnCube,
                                 y4 = y1 + 1 + lengthOfCube - positionOnCube) {


                        if (board[y2][x2].getSide() == side
                                && board[y3][x3].getSide() == side
                                && board[y4][x4].getSide() == side) {
                            String coordinatesDto = "("+ x1 +"; "+ y1 +
                                    "), ("+ x2 +"; "+ y2 +
                                    "), ("+ x3 +"; "+ y3 +
                                    "), ("+ x4 +"; "+ y4 +")";

                            switch (side) {
                                case BLACK -> {
                                    return "b "+coordinatesDto;
                                }
                                case WHITE -> {
                                    return "w "+coordinatesDto;
                                }
                            }
                        }
                    }


                }


            }

        }
        if (isDraw) {
            return "d";
        }else {
            return "";
        }

    }

    @Override
    public void setSideOnBoard(Cell[][] board, SimpleMove simpleMove) {
        if(board[simpleMove.getY()][simpleMove.getX()].getSide() == null) {
            board[simpleMove.getY()][simpleMove.getX()].setSide(simpleMove.getSide());
        }

    }
}
