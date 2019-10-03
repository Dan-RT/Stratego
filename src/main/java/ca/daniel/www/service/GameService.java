package ca.daniel.www.service;

import ca.daniel.www.exception.SquareNotEmptyException;
import ca.daniel.www.model.Coordinate;
import ca.daniel.www.model.Game;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.customEnum.PieceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    public Game initGame() {
        Game game = new Game();
        game.init();
        return game;
    }

    public Game setGame() {
        Game game = new Game();
        game.init();
        game.randomSet();
        return game;
    }


    public static Piece[][] initBoard() {
        Piece[][] board = new Piece[10][10];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if ((i == 2 || i == 3 || i == 6 || i == 7) && (j == 4 || j == 5)) {
                    Piece piece = new Piece();
                    piece.setType(PieceType.LAKE);
                    piece.setCoordinate(new Coordinate(i, j));
                    board[j][i] = piece;

                    //new Piece(new ObjectId(), PieceType.LAKE, new Coordinate(i, j));
                } else {
                    Piece piece = new Piece();
                    piece.setType(PieceType.NONE);
                    piece.setCoordinate(new Coordinate(i, j));
                    board[j][i] = piece;

                    //board[j][i] = new Piece(new ObjectId(), PieceType.NONE, new Coordinate(i, j));
                }
            }
        }

        return board;
    }

    public static void displayBoard(Piece[][] board) {
        System.out.print("\n");
        for(int i = 0; i < board.length; i++) {
            System.out.print("\n");
            for(int j = 0; j < board[i].length; j++) {
                int rank = board[i][j].getType().getRank();
                String output = "|";
                if (rank < 10 && rank >= 0) {
                    output = output + " ";
                }
                output = output + board[i][j].getType().getRank();
                if (j == 9) {
                    output = output + "|";
                }
                System.out.print(output);
            }
        }
        System.out.print("\n");
    }

    public static Piece[][] setPieceOnBoard(Piece[][] board, Coordinate prevCoordinate, Coordinate coordinate, Piece piece) {
        if (prevCoordinate != null) {
            Piece pieceTmp = new Piece();
            pieceTmp.setType(PieceType.NONE);
            pieceTmp.setCoordinate(new Coordinate(prevCoordinate.getY(), prevCoordinate.getX()));
            board[prevCoordinate.getY()][prevCoordinate.getX()] = pieceTmp;

            //this.board[prevCoordinate.getY()][prevCoordinate.getX()] = new Piece(new ObjectId(), PieceType.NONE, new Coordinate(prevCoordinate.getY(), prevCoordinate.getX()));
        }
        board[coordinate.getY()][coordinate.getX()] = piece;

        return board;
    }


    public static void setPieces(Piece[][] board, List<Piece> pieces) throws Exception, SquareNotEmptyException {
        for (Piece piece : pieces) {
            if (TurnService.squareIsEmpty(board, piece.getCoordinate())) {
                setPieceOnBoard(board,null, piece.getCoordinate(), piece);
            } else {
                throw new SquareNotEmptyException();
            }
        }
    }

}
