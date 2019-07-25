package ca.daniel.www.model;

import java.util.List;

public class Board {
    int[][] board = new int[10][10];
    
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

    public void setPieceOnBoard(Piece piece) throws Exception {
        int x = piece.getX();
        int y = piece.getY();

        if (isEmpty(x, y)) {
            this.board[y][x] = piece.getType().getRank();
        } else {
            throw new Exception("(" + x + "," + y + " square not empty");
        }
    }

    public void setPieces(List<Piece> pieces) throws Exception {
        for (Piece piece : pieces) {
            setPieceOnBoard(piece);
        }
    }

    private boolean isEmpty(int x, int y) {
        return (board[y][x] == -1 || board[y][x] == -2);
    }

}
