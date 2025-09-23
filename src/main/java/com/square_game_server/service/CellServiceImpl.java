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
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                Side side = board[i][j].getSide();
                if (side == null) {
                    isDraw = false;
                }
                for (int lengthOfCube = 0; side != null
                        && i + lengthOfCube < board.length
                        && j + lengthOfCube < board.length; lengthOfCube++) {


                    for (int positionOnCube = 0; positionOnCube <= lengthOfCube
                            && i + positionOnCube < board.length
                            && j + 1 + lengthOfCube - positionOnCube < board.length
                            && j + 1 + lengthOfCube - positionOnCube >= 0
                            && i + 1 + lengthOfCube < board.length
                            && j + 1 - lengthOfCube - positionOnCube < board.length
                            && j + 1 - lengthOfCube - positionOnCube >= 0
                            && i + 1 + lengthOfCube - positionOnCube < board.length
                            && i + 1 + lengthOfCube - positionOnCube >= 0
                            && j - positionOnCube >= 0; positionOnCube++) {

                        if (board[i + positionOnCube][j + 1 + lengthOfCube - positionOnCube].getSide() == side
                                && board[i + 1 + lengthOfCube][j + 1 + lengthOfCube - positionOnCube * 2].getSide() == side
                                && board[i + 1 + lengthOfCube - positionOnCube][j - positionOnCube].getSide() == side) {



                            switch (side) {
                                case BLACK -> {
                                    return "b";
                                }
                                case WHITE -> {
                                    return "w";
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
