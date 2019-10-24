package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"gameId", "board", "pieceAttacking", "pieceAttacked"})
public class Attack extends JacksonObject {
    @NotNull
    @JsonProperty("gameId")
    private String gameId;
    @NotNull
    @JsonProperty("board")
    private Piece[][] board;
    @NotNull
    @Valid
    @JsonProperty("pieceAttacking")
    private Piece pieceAttacking;
    @NotNull
    @Valid
    @JsonProperty("pieceAttacked")
    private Piece pieceAttacked;
    @NotNull
    @Valid
    @JsonProperty("playerAttacking")
    private Player playerAttacking;
    @NotNull
    @Valid
    @JsonProperty("playerAttacked")
    private Player playerAttacked;

    public Attack() {
        super();
    }

    @JsonProperty("gameId")
    public String getGameId() {
        return gameId;
    }
    @JsonProperty("gameId")
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    @JsonProperty("board")
    public Piece[][] getBoard() {
        return board;
    }
    @JsonProperty("board")
    public void setBoard(Piece[][] board) {
        this.board = board;
    }
    @JsonProperty("pieceAttacking")
    public Piece getPieceAttacking() {
        return pieceAttacking;
    }
    @JsonProperty("pieceAttacking")
    public void setPieceAttacking(Piece pieceAttacking) {
        this.pieceAttacking = pieceAttacking;
    }
    @JsonProperty("pieceAttacked")
    public Piece getPieceAttacked() {
        return pieceAttacked;
    }
    @JsonProperty("pieceAttacked")
    public void setPieceAttacked(Piece pieceAttacked) {
        this.pieceAttacked = pieceAttacked;
    }
    @JsonProperty("playerAttacking")
    public Player getPlayerAttacking() {
        return playerAttacking;
    }
    @JsonProperty("playerAttacking")
    public void setPlayerAttacking(Player playerAttacking) {
        this.playerAttacking = playerAttacking;
    }
    @JsonProperty("playerAttacked")
    public Player getPlayerAttacked() {
        return playerAttacked;
    }
    @JsonProperty("playerAttacked")
    public void setPlayerAttacked(Player playerAttacked) {
        this.playerAttacked = playerAttacked;
    }

}
