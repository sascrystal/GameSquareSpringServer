package com.square_game_server.rest.Dto;

import com.square_game_server.domain.Side;
import com.square_game_server.domain.SimpleMove;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class SimpleMoveDto {
    private int x;
    private int y;
    private String color;
    public static SimpleMove dtoToDomainObject(SimpleMoveDto simpleMoveDto){
        if (simpleMoveDto.getColor().equals("w")) {
            return new SimpleMove(simpleMoveDto.getX(), simpleMoveDto.getY(), Side.WHITE);
        }
        return new SimpleMove(simpleMoveDto.getX(), simpleMoveDto.getY(), Side.BLACK);
    }
    public static SimpleMoveDto domainObjectToDto(SimpleMove simpleMove){
        if (Objects.requireNonNull(simpleMove.getSide()) == Side.WHITE) {
            return new SimpleMoveDto(simpleMove.getX(), simpleMove.getY(), "w");
        }
        return new SimpleMoveDto(simpleMove.getX(), simpleMove.getY(), "b");
    }
}
