package com.square_game_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board {
    private Cell[][] data;
    private Side nextPlayerColor;
    public Cell getCell(int x, int y) {
        return data[y][x];
    }
}
