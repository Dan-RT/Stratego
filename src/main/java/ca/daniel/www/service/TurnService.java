package ca.daniel.www.service;

import ca.daniel.www.model.Coordinate;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.Turn;
import ca.daniel.www.model.customEnum.Movement;
import ca.daniel.www.model.customEnum.PieceType;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    public Turn movePieceOnBoard(Turn turn) {
        if (isAuthorized(turn.getBoard(), turn.getPieceToMove(), turn.getAction(), false)) {
            turn.setBoard(GameService.setPieceOnBoard(turn.getBoard(), turn.getPieceToMove().getCoordinate(), turn.getAction(), turn.getPieceToMove()));
            turn.setAuthorized(true);
        } else {
            turn.setAuthorized(false);
        }
        //GameService.displayBoard(turn.getBoard());
        return turn;
    }

    public static boolean isAuthorized(Piece[][] board, Piece piece, Coordinate action, boolean attack) {

        if (piece.getMovement().getType().equals("IDLE")) {
            return false;
        }

        boolean moveAuthozired = checkMovement(piece.getCoordinate(), action, piece.getMovement());

        if (!moveAuthozired) {
            return false;
        }

        if (piece.getMovement().getType().equals("NORMAL")) {
            if (attack) {
                return true;
            } else {
                return squareIsEmpty(board, action);
            }
        }

        if (piece.getMovement().getType().equals("JUMP")) {
            return checkNeighbours(board, piece.getCoordinate(), action, attack);
        }

        return false;
    }

    private static boolean checkMovement(Coordinate prevCoordinates, Coordinate coordinate, Movement movement) {
        int prevX = prevCoordinates.getX();
        int prevY = prevCoordinates.getY();
        int x = coordinate.getX();
        int y = coordinate.getY();

        switch (movement.getType()) {
            case "IDLE":
                return false;
            case "NORMAL":
                if ((Math.abs(prevX-x) == 1) && (Math.abs(prevY-y) == 0)) {
                    return true;
                }
                if ((Math.abs(prevX-x) == 0) && (Math.abs(prevY-y) == 1)) {
                    return true;
                }
                return false;
            case "JUMP":
                if (Math.abs(prevX-x) > 0 && Math.abs(prevX-x) < 10 && Math.abs(prevY-y) == 0) {
                    return true;
                }
                if (Math.abs(prevY-y) > 0 && Math.abs(prevY-y) < 10 && Math.abs(prevX-x) == 0) {
                   return true;
                }
                return false;
        }
        return false;
    }

    public static boolean squareIsEmpty (Piece board[][], Coordinate coordinate) {
        PieceType tmp = board[coordinate.getX()][coordinate.getY()].getType();

        return (tmp == PieceType.NONE);
    }

    public static boolean checkNeighbours(Piece board[][], Coordinate prevCoordinates, Coordinate action, boolean attack) {

        int gap = 0;
        boolean gapX = false;
        Coordinate coordinates = new Coordinate();

        if (prevCoordinates.getX() - action.getX() != 0) {
            gap = action.getX() - prevCoordinates.getX();
            gapX = true;
        } else if (prevCoordinates.getY() - action.getY() != 0) {
            gap = action.getY() - prevCoordinates.getY();
            gapX = false;
        }

        int gapInitial = gap;

        do {

            if (gapX) {
                coordinates.setX(prevCoordinates.getX() + gap);
                coordinates.setY(prevCoordinates.getY());
            } else {
                coordinates.setX(prevCoordinates.getX());
                coordinates.setY(prevCoordinates.getY() + gap);
            }

            if (gapInitial == gap) {
                if (!attack && !squareIsEmpty(board, coordinates)) {
                    return false;
                }
            }

            if (gap > 0) {
                gap--;
            } else {
                gap++;
            }
        } while (gap != 0);

        return true;
    }

}
