package ca.daniel.www.model;

import ca.daniel.www.service.GameService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"board", "players", "playingPlayer"})
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
    private List<Player> players;
    @NotNull
    @JsonProperty("playingPlayer")
    private int playingPlayer;

    public Game() {
        players = new ArrayList<>();
    }

    public void init() {
        board = GameService.initBoard();
    }

    @JsonProperty("_id")
    public String get_id() {
        return _id;
    }
    @JsonProperty("_id")
    public void set_id(String _id) {
        this._id = _id;
    }
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
    @JsonProperty("playingPlayer")
    public int getPlayingPlayer() {
        return playingPlayer;
    }
    @JsonProperty("playingPlayer")
    public void setPlayingPlayer(int playingPlayer) {
        this.playingPlayer = playingPlayer;
    }
    public void updatePlayingPlayer() {
        if (this.playingPlayer == 1) {
            this.playingPlayer = 2;
        } else {
            this.playingPlayer = 1;
        }
    }
}




/*
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
    */
