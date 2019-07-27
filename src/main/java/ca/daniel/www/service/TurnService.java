package ca.daniel.www.service;

import ca.daniel.www.exception.ActionNotAuthorizedException;
import ca.daniel.www.model.Coordinate;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.Turn;
import ca.daniel.www.model.customEnum.Movement;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    public void movePieceOnBoard(Turn turn) throws ActionNotAuthorizedException {
        if (isAuthorized(turn)) {
            turn.getBoard().setPieceOnBoard(turn.getAction(),turn.getPieceToMove().getType().getRank());
        } else {
            throw new ActionNotAuthorizedException();
        }
    }

    private boolean isAuthorized(Turn turn) {
        Piece piece = turn.getPieceToMove();

        if (piece.getMovement().getType().equals("IDLE")) {
            return false;
        }

        Coordinate action = checkMovement(piece.getCoordinate(), turn.getAction(), piece.getMovement());

        if (action == null) {
            return false;
        }

        if (piece.getMovement().getType().equals("NORMAL") && squareIsEmpty(turn.getBoard().getBoard(), turn.getAction())) {
            return true;
        }

        if (piece.getMovement().getType().equals("JUMP")) {
            checkNeighbours(turn.getBoard().getBoard(), piece.getCoordinate(), action);
        }

        return false;
    }

    private Coordinate checkMovement(Coordinate prevCoordinates, Coordinate coordinate, Movement movement) {
        int prevX = prevCoordinates.getX();
        int prevY = prevCoordinates.getY();
        int x = coordinate.getX();
        int y = coordinate.getY();

        Coordinate move = new Coordinate(x, y);

        switch (movement.getType()) {
            case "IDLE":
                return null;
            case "NORMAL":
                if ((Math.abs(prevX-x) == 1) && (Math.abs(prevY-y) == 0)) {
                    move.setX(prevX-x);
                    move.setY(0);
                    return move;
                }
                if ((Math.abs(prevX-x) == 0) && (Math.abs(prevY-y) == 1)) {
                    move.setX(0);
                    move.setY(prevY-y);
                    return move;
                }
                return null;
            case "JUMP":
                if ((Math.abs(prevX-x) > 0 && (Math.abs(prevX-x) < 10) && (Math.abs(prevY-y) == 0))) {
                    move.setX(prevX-x);
                    move.setY(0);
                    return move;
                }
                return null;
        }
        return null;
    }

    public static boolean squareIsEmpty (int board[][], Coordinate coordinate) {
        return (board[coordinate.getY()][coordinate.getX()] == -2);
    }

    public boolean checkNeighbours(int board[][], Coordinate prevCoordinates, Coordinate action) {
        int gap = action.getX();
        int tmp = gap;
        Coordinate coordinates = new Coordinate();

        do {
            coordinates.setX(prevCoordinates.getX() + gap);
            coordinates.setY(prevCoordinates.getY());
            if (!squareIsEmpty(board, coordinates)) {
                return false;
            }
            gap--;
        } while (gap == 0);

        return true;
    }

}
