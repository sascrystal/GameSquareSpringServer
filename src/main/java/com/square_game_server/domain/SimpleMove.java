package com.square_game_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMove {
    private int x;
    private int y;
    private Side side;
}
