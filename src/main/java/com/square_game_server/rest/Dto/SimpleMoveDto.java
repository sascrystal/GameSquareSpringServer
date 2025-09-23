package com.square_game_server.rest.Dto;

import com.square_game_server.domain.Side;
import com.square_game_server.domain.SimpleMove;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMoveDto {
    private int x;
    private int y;
    private String color;
    public static SimpleMove dtoToDomainObject(SimpleMoveDto simpleMoveDto){
        switch (simpleMoveDto.getColor()){
            case "w":
                    return new SimpleMove(simpleMoveDto.getX(), simpleMoveDto.getY(), Side.WHITE);
                    default:
                        return new SimpleMove(simpleMoveDto.getX(), simpleMoveDto.getY(), Side.BLACK);
        }
    }
    public static SimpleMoveDto domainObjectToDto(SimpleMove simpleMove){
        switch (simpleMove.getSide()){
            case WHITE:
                return new SimpleMoveDto(simpleMove.getX(), simpleMove.getY(), "w");
                default:
                    return new SimpleMoveDto(simpleMove.getX(), simpleMove.getY(), "b");
        }
    }
}
