package ca.daniel.www.model;

import ca.daniel.www.model.customEnum.Movement;
import ca.daniel.www.model.customEnum.PieceType;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    List<Player> players;
    List<Piece> pieces1;
    List<Piece> pieces2;

    public Game() {
        pieces1 = new ArrayList<>();
        pieces2 = new ArrayList<>();
        players = new ArrayList<>();
        board = new Board();
    }

    public void init() {
        initPieces(pieces1, 1);
        initPieces(pieces2, 2);
        board.init();
    }

    private List<Piece> initPieces(List<Piece> pieces, int team) {
        for(int i = 0; i < 8; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.SCOUT, 8, Movement.JUMP, team, true, coordinate));
        }
        for(int i = 0; i < 5; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.MINER, 5, Movement.NORMAL, team, true, coordinate));
        }
        for(int i = 0; i < 4; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.SERGEANT, 4, Movement.NORMAL, team, true, coordinate));
        }
        for(int i = 0; i < 4; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.LIEUTENANT, 4, Movement.NORMAL, team, true, coordinate));
        }
        for(int i = 0; i < 4; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.CAPTAIN, 4, Movement.NORMAL, team, true, coordinate));
        }
        for(int i = 0; i < 3; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.MAJOR, 3, Movement.NORMAL, team, true, coordinate));
        }
        for(int i = 0; i < 2; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.COLONEL, 2, Movement.NORMAL, team, true, coordinate));
        }
        pieces.add(new Piece(new ObjectId(), PieceType.GENERAL, 1, Movement.NORMAL, team, true, coordinate));
        pieces.add(new Piece(new ObjectId(), PieceType.MARSHAL, 1, Movement.NORMAL, team, true, coordinate));

        for(int i = 0; i < 6; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.BOMB, 6, Movement.IDLE, team, true, coordinate));
        }

        pieces.add(new Piece(new ObjectId(), PieceType.FLAG, 1, Movement.IDLE, team, true, coordinate));
        pieces.add(new Piece(new ObjectId(), PieceType.SPY, 1, Movement.NORMAL, team, true, coordinate));

        return pieces;
    }

    public void randomPieceSetter(List<Piece> pieces, int team) {
        int minY;
        int maxY;

        if (team == 1) {
            minY = 0;
            maxY = 4;
        } else {
            minY = 6;
            maxY = 9;
        }

        int x = 0;
        int y = minY;
        for (Piece piece : pieces) {
            if (x > 9) {
                x = 0;
                y++;
            }
            if (y > maxY) {
                y = minY;
            }
            piece.setX(x);
            piece.setY(y);
            x++;
        }
    }

    public void randomSet() {
        randomPieceSetter(pieces1, 1);
        randomPieceSetter(pieces2, 2);
        for (Piece piece:pieces1) {
            try {
                board.setPieceOnBoard(piece);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Piece piece:pieces2) {
            try {
                board.setPieceOnBoard(piece);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Piece> getPieces1() {
        return this.pieces1;
    }

    public List<Piece> getPieces2() {
        return this.pieces2;
    }

    public Board getBoard() {
        return this.board;
    }
}
