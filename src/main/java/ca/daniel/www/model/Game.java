package ca.daniel.www.model;

import ca.daniel.www.model.customEnum.Movement;
import ca.daniel.www.model.customEnum.PieceType;
import ca.daniel.www.service.GameService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"board", "players", "pieces1", "pieces2"})
@Document(collection = "game")
public class Game extends JacksonObject {
    @Id
    private String _id;
    @NotNull
    @Valid
    @JsonProperty("board")
    private Piece[][] board;
    @NotNull
    @Valid
    @JsonProperty("players")
    List<Player> players;
    @NotNull
    @Valid
    @JsonProperty("pieces1")
    List<Piece> pieces1;
    @NotNull
    @Valid
    @JsonProperty("pieces2")
    List<Piece> pieces2;

    public Game() {
        pieces1 = new ArrayList<>();
        pieces2 = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void init() {
        initPieces(pieces1, 1);
        initPieces(pieces2, 2);
        board = GameService.initBoard();
    }

    private List<Piece> initPieces(List<Piece> pieces, int team) {
        for(int i = 0; i < 8; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.SCOUT, 8, Movement.JUMP, team, true, new Coordinate()));
        }
        for(int i = 0; i < 5; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.MINER, 5, Movement.NORMAL, team, true, new Coordinate()));
        }
        for(int i = 0; i < 4; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.SERGEANT, 4, Movement.NORMAL, team, true, new Coordinate()));
        }
        for(int i = 0; i < 4; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.LIEUTENANT, 4, Movement.NORMAL, team, true, new Coordinate()));
        }
        for(int i = 0; i < 4; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.CAPTAIN, 4, Movement.NORMAL, team, true, new Coordinate()));
        }
        for(int i = 0; i < 3; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.MAJOR, 3, Movement.NORMAL, team, true, new Coordinate()));
        }
        for(int i = 0; i < 2; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.COLONEL, 2, Movement.NORMAL, team, true, new Coordinate()));
        }
        pieces.add(new Piece(new ObjectId(), PieceType.GENERAL, 1, Movement.NORMAL, team, true, new Coordinate()));
        pieces.add(new Piece(new ObjectId(), PieceType.MARSHAL, 1, Movement.NORMAL, team, true, new Coordinate()));

        for(int i = 0; i < 6; i++) {
            pieces.add(new Piece(new ObjectId(), PieceType.BOMB, 6, Movement.IDLE, team, true, new Coordinate()));
        }

        pieces.add(new Piece(new ObjectId(), PieceType.FLAG, 1, Movement.IDLE, team, true, new Coordinate()));
        pieces.add(new Piece(new ObjectId(), PieceType.SPY, 1, Movement.NORMAL, team, true, new Coordinate()));

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
            piece.getCoordinate().setX(x);
            piece.getCoordinate().setY(y);
            x++;
        }
    }

    public void randomSet() {
        randomPieceSetter(pieces1, 1);
        randomPieceSetter(pieces2, 2);
        for (Piece piece:pieces1) {
            try {
                GameService.setPieceOnBoard(board, null, piece.getCoordinate(), piece);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Piece piece:pieces2) {
            try {
                GameService.setPieceOnBoard(board,null, piece.getCoordinate(), piece);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @JsonProperty("_id")
    public String get_id() {
        return _id;
    }
    @JsonProperty("_id")
    public void set_id(String _id) {
        this._id = _id;
    }
    @JsonProperty("pieces1")
    public List<Piece> getPieces1() {
        return this.pieces1;
    }
    @JsonProperty("pieces1")
    public void setPieces1(List<Piece> pieces1) {
        this.pieces1 = pieces1;
    }
    @JsonProperty("pieces2")
    public List<Piece> getPieces2() {
        return this.pieces2;
    }
    @JsonProperty("pieces2")
    public void setPieces2(List<Piece> pieces2) {
        this.pieces2 = pieces2;
    }
    @JsonProperty("board")
    public Piece[][] getBoard() {
        return this.board;
    }
    @JsonProperty("board")
    public void setBoard(Piece[][] board) {
        this.board = board;
    }
    @JsonProperty("players")
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void addPlayer(Player player){
        this.players.add(player);
    }
    @JsonProperty("players")
    public List<Player> getPlayers() {
        return players;
    }
}
