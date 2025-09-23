package com.square_game_server.rest.Dto;

import com.square_game_server.domain.Cell;
import com.square_game_server.domain.Side;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardDto {
    private String board;

    public static Cell[][] boardDtoToDomainObject(String board, int size){
        Cell[][] domainObject = new Cell[size][size];
        int z = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                switch (board.charAt(z)){
                    case 'b':
                        domainObject[i][j] = new Cell(Side.BLACK);
                        break;
                        case 'w':
                            domainObject[i][j] = new Cell(Side.WHITE);
                            break;
                            case ' ':
                                domainObject[i][j] = new Cell();
                                break;
                }
                z++;
            }
        }
        return domainObject;
    }
    public static String domainObjectToBoardDto(Cell[][] domainObject){
        StringBuilder stringBuilder = new StringBuilder();
        for (Cell[] cells : domainObject) {
            for (Cell cell : cells) {
                switch (cell.getSide()){
                    case BLACK:
                        stringBuilder.append('b');
                        break;
                        case WHITE:
                            stringBuilder.append('w');
                            break;
                    default:
                        stringBuilder.append(' ');
                        break;
                }

            }
        }
        return stringBuilder.toString();
    }


}
