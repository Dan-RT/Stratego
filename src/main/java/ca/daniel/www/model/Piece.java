package ca.daniel.www.model;

import ca.daniel.www.model.customEnum.Movement;
import ca.daniel.www.model.customEnum.PieceType;
import org.bson.types.ObjectId;

public class Piece {
    private final ObjectId _id;
    private final PieceType type;
    private final int maxNumber;
    private final Movement movement;
    private final int team;
    private boolean alive;
    private Coordinate coordinate;

    public Piece(ObjectId id, PieceType type, int maxNumber, Movement movement, int team, boolean alive, Coordinate coordinate) {
        this._id = id;
        this.type = type;
        this.maxNumber = maxNumber;
        this.movement = movement;
        this.team = team;
        this.alive = alive;
    }

    public ObjectId get_id() {
        return _id;
    }

    public PieceType getType() {
        return type;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public Movement getMovement() {
        return movement;
    }

    public int getTeam() {
        return team;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
