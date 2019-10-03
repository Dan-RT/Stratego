package ca.daniel.www.model;

import ca.daniel.www.model.customEnum.Movement;
import ca.daniel.www.model.customEnum.PieceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"_id", "type", "maxNumber", "movement", "team", "alive", "coordinate"})
public class Piece extends JacksonObject {
    @JsonProperty("_id")
    private ObjectId _id;
    @NotNull
    @Valid
    @JsonProperty("type")
    private PieceType type;
    @NotNull
    @JsonProperty("maxNumber")
    private int maxNumber;
    @NotNull
    @Valid
    @JsonProperty("movement")
    private Movement movement;
    @NotNull
    @JsonProperty("team")
    private int team;
    @NotNull
    @JsonProperty("alive")
    private boolean alive;
    @NotNull
    @Valid
    @JsonProperty("coordinate")
    private Coordinate coordinate;

    /*public Piece(ObjectId id, PieceType type, Coordinate coordinate) {
        this._id = id;
        this.type = type;
        this.maxNumber = -1;
        this.movement = null;
        this.team = -1;
        this.alive = false;
        this.coordinate = coordinate;
    }*/

    public Piece (){

    }

    public Piece(ObjectId id, PieceType type, int maxNumber, Movement movement, int team, boolean alive, Coordinate coordinate) {
        this._id = id;
        this.type = type;
        this.maxNumber = maxNumber;
        this.movement = movement;
        this.team = team;
        this.alive = alive;
        this.coordinate = coordinate;
    }

    @JsonProperty("coordinate")
    public Coordinate getCoordinate() {
        return coordinate;
    }
    @JsonProperty("coordinate")
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    @JsonProperty("alive")
    public boolean isAlive() {
        return alive;
    }
    @JsonProperty("alive")
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    @JsonProperty("team")
    public int getTeam() {
        return team;
    }
    @JsonProperty("team")
    public void setTeam(int team) {
        this.team = team;
    }
    @JsonProperty("movement")
    public Movement getMovement() {
        return movement;
    }
    @JsonProperty("movement")
    public void setMovement(Movement movement) {
        this.movement = movement;
    }
    @JsonProperty("maxNumber")
    public int getMaxNumber() {
        return maxNumber;
    }
    @JsonProperty("maxNumber")
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }
    @JsonProperty("type")
    public PieceType getType() {
        return type;
    }
    @JsonProperty("type")
    public void setType(PieceType type) {
        this.type = type;
    }
    @JsonProperty("_id")
    public ObjectId get_id() {
        return _id;
    }
    @JsonProperty("_id")
    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
