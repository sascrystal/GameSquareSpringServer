package com.square_game_server.service;

import com.square_game_server.config.BotPriorityConfig;
import com.square_game_server.domain.Board;
import com.square_game_server.domain.Cell;
import com.square_game_server.domain.Side;
import com.square_game_server.domain.SimpleMove;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
@AllArgsConstructor
public class BotServicePriority implements BotService {
    private final BotPriorityConfig config;


    @Override
    public SimpleMove doMove(Board board) {
        int[][] cellsPriority = new int[board.getData().length][board.getData().length];
        Side enemySide = (board.getNextPlayerColor() == Side.WHITE) ? Side.BLACK : Side.WHITE;
        for (int y1 = 0; y1 < board.getData().length; y1++) {
            for (int x1 = 0; x1 < board.getData()[y1].length; x1++) {
                int[][] potentialCombination = new int[4][2];
                potentialCombination[0][0] = x1;
                potentialCombination[0][1] = y1;
                for (int lengthOfSquare = 0; y1 + lengthOfSquare < board.getData().length; lengthOfSquare++) {
                    for (int positionOnSquare = 0;
                         positionOnSquare <= lengthOfSquare
                                 && potentialCombination[1][1] < board.getData().length
                                 && potentialCombination[2][0] >= 0
                                 && potentialCombination[2][1] < board.getData().length
                                 && potentialCombination[3][0] >= 0
                                 && potentialCombination[3][1] < board.getData().length;
                         positionOnSquare++,
                                 potentialCombination[1][0] = x1 + 1 + lengthOfSquare - positionOnSquare,
                                 potentialCombination[1][1] = y1 + positionOnSquare,
                                 potentialCombination[2][0] = x1 + 1 + lengthOfSquare - positionOnSquare * 2,
                                 potentialCombination[2][1] = y1 + 1 + lengthOfSquare,
                                 potentialCombination[3][0] = x1 - positionOnSquare,
                                 potentialCombination[3][1] = y1 + 1 + lengthOfSquare - positionOnSquare) {

                        if (potentialCombination[2][0] >= board.getData().length || potentialCombination[1][0] >= board.getData().length) {
                            continue;
                        }
                        int countFriendlyPieces = countPiecesOfSide(potentialCombination, board, board.getNextPlayerColor());
                        int countEnemyPieces = countPiecesOfSide(potentialCombination, board, enemySide);
                        if (countFriendlyPieces == 3 || countEnemyPieces == 3) {
                            for (int[] coordinate : potentialCombination) {
                                if (board.getCell(coordinate[0], coordinate[1]).getSide() == null) {
                                    return new SimpleMove(coordinate[0], coordinate[1], board.getNextPlayerColor());
                                }
                            }
                        }
                        if (countEnemyPieces == 0) {
                            int givingPoints = switch (countFriendlyPieces) {
                                case 0 -> config.getPointsForFriendlyPiece0();
                                case 1 -> config.getPointsForFriendlyPiece1();
                                case 2 -> config.getPointsForFriendlyPiece2();
                                default -> 0;
                            };
                            cellsPriority[y1][x1] += givingPoints;
                        }
                        if (countFriendlyPieces == 0) {
                            int givingPoints = switch (countEnemyPieces) {
                                case 0 -> config.getPointsForEnemyPiece0();
                                case 1 -> config.getPointsForEnemyPiece1();
                                case 2 -> config.getPointsForEnemyPiece2();
                                default -> 0;
                            };
                            cellsPriority[y1][x1] += givingPoints;

                        }


                    }
                }
            }
        }
        for (int y1 = 0; y1 < board.getData().length; y1++) {
            for (int x1 = 0; x1 < board.getData()[y1].length; x1++) {
                if (board.getCell(x1, y1).getSide() != null) {
                    cellsPriority[y1][x1] = -1;
                }
            }
        }
        ArrayList<int[]> allMaxPriorityIndexes = findAllMaxIndexes(cellsPriority);
        Random random = new Random();
        int index = random.nextInt(allMaxPriorityIndexes.size());
        int[] coordinateOfNextMove = allMaxPriorityIndexes.get(index);
        return new SimpleMove(coordinateOfNextMove[1], coordinateOfNextMove[0], board.getNextPlayerColor());

    }

    private int countPiecesOfSide(int[][] coordinates, Board board, Side side) {
        int count = 0;
        for (int[] coordinate : coordinates) {
            if (board.getCell(coordinate[0], coordinate[1]).getSide() == side) {
                count++;
            }
        }
        return count;
    }

    private static ArrayList<int[]> findAllMaxIndexes(int[][] matrix) {
        ArrayList<int[]> maxIndexes = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return maxIndexes;
        }

        int maxValue = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > maxValue) {
                    maxValue = matrix[i][j];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == maxValue) {
                    maxIndexes.add(new int[]{i, j});
                }
            }
        }

        return maxIndexes;
    }
}

