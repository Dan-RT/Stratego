package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"board", "pieceAttacking", "pieceAttacked"})
public class Attack extends JacksonObject {
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

    public Attack() {
        super();
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
}
