package ca.daniel.www.service;

import ca.daniel.www.model.Coordinate;
import ca.daniel.www.model.Piece;
import ca.daniel.www.model.Turn;
import ca.daniel.www.model.customEnum.Movement;

public class TurnService {


    public boolean isAuthorized(Piece piece) {

        if (piece.getMovement().getType() == "NORMAL") {

        }

        if (piece.getMovement().getType() == "JUMP") {

        }

        if (piece.getMovement().getType() == "IDLE") {
            return false;
        }

        //check movement
        //check neighbours
        //check lake

        return false;
    }

    private Coordinate checkMovement(Coordinate prevCoordinate, Coordinate coordinate, Movement movement) {
        int prevX = prevCoordinate.getX();
        int prevY = prevCoordinate.getY();
        int x = coordinate.getX();
        int y = coordinate.getY();

        Coordinate move = new Coordinate();

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


}
