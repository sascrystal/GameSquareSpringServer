package com.square_game_server.service;

import com.square_game_server.domain.Board;
import com.square_game_server.domain.SimpleMove;

public interface BotService {
    SimpleMove doMove(Board board);
}
