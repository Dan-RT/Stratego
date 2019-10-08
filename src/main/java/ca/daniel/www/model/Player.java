package ca.daniel.www.model;

import ca.daniel.www.dao.PlayerDao;
import ca.daniel.www.model.customEnum.Movement;
import ca.daniel.www.model.customEnum.PieceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"_id", "playerId", "name", "team"})
@Document(collection = "player")
public class Player extends JacksonObject {
    @Id
    @JsonProperty("_id")
    private String _id;
    @NotNull
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotNull
    @JsonProperty("team")
    private int team;
    @NotNull
    @JsonProperty("ready")
    private boolean ready;
    @NotNull
    @JsonProperty("playing")
    private boolean playing;
    @NotNull
    @JsonProperty("pieces")
    private List<Piece> pieces;
    private PlayerDao playerDao;

    @Autowired
    public Player(PlayerDao playerDao) {
        super();
        this.playerDao = playerDao;
        this.pieces = new ArrayList<>();
        this.initPieces();
    }

    private void initPieces() {
        for(int i = 0; i < 8; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.SCOUT, 8, Movement.JUMP, this.team, true, new Coordinate()));
        }
        for(int i = 0; i < 5; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.MINER, 5, Movement.NORMAL, this.team, true, new Coordinate()));
        }
        for(int i = 0; i < 4; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.SERGEANT, 4, Movement.NORMAL, this.team, true, new Coordinate()));
        }
        for(int i = 0; i < 4; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.LIEUTENANT, 4, Movement.NORMAL, this.team, true, new Coordinate()));
        }
        for(int i = 0; i < 4; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.CAPTAIN, 4, Movement.NORMAL, this.team, true, new Coordinate()));
        }
        for(int i = 0; i < 3; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.MAJOR, 3, Movement.NORMAL, this.team, true, new Coordinate()));
        }
        for(int i = 0; i < 2; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.COLONEL, 2, Movement.NORMAL, this.team, true, new Coordinate()));
        }
        this.pieces.add(new Piece(new ObjectId(), PieceType.GENERAL, 1, Movement.NORMAL, this.team, true, new Coordinate()));
        this.pieces.add(new Piece(new ObjectId(), PieceType.MARSHAL, 1, Movement.NORMAL, this.team, true, new Coordinate()));

        for(int i = 0; i < 6; i++) {
            this.pieces.add(new Piece(new ObjectId(), PieceType.BOMB, 6, Movement.IDLE, this.team, true, new Coordinate()));
        }

        this.pieces.add(new Piece(new ObjectId(), PieceType.FLAG, 1, Movement.IDLE, this.team, true, new Coordinate()));
        this.pieces.add(new Piece(new ObjectId(), PieceType.SPY, 1, Movement.NORMAL, this.team, true, new Coordinate()));

    }

    public Player update() {
        return playerDao.updatePlayer(this);
    }

    @JsonProperty("_id")
    public String get_id() {
        return _id;
    }
    @JsonProperty("_id")
    public void set_id(String _id) {
        this._id = _id;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("team")
    public int getTeam() {
        return team;
    }
    @JsonProperty("team")
    public void setTeam(int team) {
        this.team = team;
    }
    @JsonProperty("ready")
    public boolean getReady() {
        return ready;
    }
    @JsonProperty("ready")
    public void setReady(boolean ready) {
        this.ready = ready;
    }
    @JsonProperty("playing")
    public boolean getPlaying() {
        return playing;
    }
    @JsonProperty("playing")
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
    @JsonProperty("pieces")
    public List<Piece> getPieces() {
        return pieces;
    }
    @JsonProperty("pieces")
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    public void addPiece(Piece piece){
        this.pieces.add(piece);
    }
}
