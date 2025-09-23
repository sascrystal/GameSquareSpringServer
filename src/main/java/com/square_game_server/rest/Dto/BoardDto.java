package com.square_game_server.rest.Dto;

import com.square_game_server.domain.Board;
import com.square_game_server.domain.Cell;
import com.square_game_server.domain.Side;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardDto {
    private int size;
    private String data;
    private String nextPlayerColor;

    public static Board boardDtoToDomainObject(BoardDto boardDto){
        Cell[][] data = new Cell[boardDto.getSize()][boardDto.getSize()];
        int z = 0;
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                switch (boardDto.getData().charAt(z)){
                    case 'b':
                        data[i][j] = new Cell(Side.BLACK);
                        break;
                        case 'w':
                            data[i][j] = new Cell(Side.WHITE);
                            break;
                            case ' ':
                                data[i][j] = new Cell();
                                break;
                }
                z++;
            }
        }
        Side side;
        if (boardDto.getNextPlayerColor().equals("w")) {
            side = Side.WHITE;
        } else {
            side = Side.BLACK;
        }
        return new Board(data, side);
    }
    public static BoardDto domainObjectToBoardDto(Board domainObject){
        StringBuilder stringBuilder = new StringBuilder();
        for (Cell[] cells : domainObject.getData()) {
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
        String nextPlayerColor;
        if(domainObject.getNextPlayerColor() == Side.WHITE){
            nextPlayerColor = "w";
        }else {
            nextPlayerColor = "b";
        }

        return new BoardDto(domainObject.getData().length, stringBuilder.toString(), nextPlayerColor);
    }


}
