package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"board","pieceToMove", "action", "authorized"})
public class Turn extends JacksonObject {
    @NotNull
    @JsonProperty("board")
    private Piece[][] board;
    /*@NotNull
    @Valid
    @JsonProperty("pieces1")
    private List<Piece> pieces1;
    @NotNull
    @Valid
    @JsonProperty("pieces2")
    private List<Piece> pieces2;*/

    @NotNull
    @Valid
    @JsonProperty("pieceToMove")
    private Piece pieceToMove;
    @NotNull
    @Valid
    @JsonProperty("action")
    private Coordinate action;
    @JsonProperty("authorized")
    private boolean authorized;


    @JsonProperty("board")
    public Piece[][] getBoard() {
        return board;
    }
    @JsonProperty("board")
    public void setBoard(Piece[][] board) {
        this.board = board;
    }
    /*
    @JsonProperty("pieces1")
    public void setPieces1(List<Piece> pieces1) {
        this.pieces1 = pieces1;
    }
    @JsonProperty("pieces1")
    public List<Piece> getPieces1() {
        return pieces1;
    }
    @JsonProperty("pieces2")
    public void setPieces2(List<Piece> pieces2) {
        this.pieces2 = pieces2;
    }
    @JsonProperty("pieces2")
    public List<Piece> getPieces2() {
        return pieces2;
    }*/
    @JsonProperty("pieceToMove")
    public Piece getPieceToMove() {
        return pieceToMove;
    }
    @JsonProperty("pieceToMove")
    public void setPieceToMove(Piece pieceToMove) {
        this.pieceToMove = pieceToMove;
    }
    @JsonProperty("action")
    public Coordinate getAction() {
        return action;
    }
    @JsonProperty("action")
    public void setAction(Coordinate action) {
        this.action = action;
    }
    @JsonProperty("authorized")
    public boolean isAuthorized() {
        return authorized;
    }
    @JsonProperty("authorized")
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
