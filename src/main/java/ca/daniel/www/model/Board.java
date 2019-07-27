package ca.daniel.www.model;

import ca.daniel.www.exception.SquareNotEmptyException;
import ca.daniel.www.service.TurnService;

import java.util.List;

public class Board extends JacksonObject {
    private int[][] board = new int[10][10];

    public Board(){
        init();
    }

    public void init() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if ((i == 2 || i == 3 || i == 6 || i == 7) && (j == 4 || j == 5)) {
                    board[j][i] = -1;
                } else {
                    board[j][i] = -2;
                }
            }
        }
    }

    public void display() {
        System.out.print("\n");
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (j == 9) {
                    System.out.println("|" + board[i][j] + "|");
                } else {
                    System.out.print("|" + board[i][j]);
                }
            }
        }
    }

    public void setPieceOnBoard(Coordinate coordinate, int rank) {
        this.board[coordinate.getY()][coordinate.getX()] = rank;
    }

    public void setPieces(List<Piece> pieces) throws Exception, SquareNotEmptyException {
        for (Piece piece : pieces) {
            if (TurnService.squareIsEmpty(this.board, piece.getCoordinate())) {
                setPieceOnBoard(piece.getCoordinate(), piece.getType().getRank());
            } else {
                throw new SquareNotEmptyException();
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
