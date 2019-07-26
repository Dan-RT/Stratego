package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"prevBoard", "board", "pieces1", "pieces2"})
public class Turn {
    @JsonProperty("id")
    private String id;
    @NotNull
    @Valid
    @JsonProperty("board")
    private Board board;
    @NotNull
    @Valid
    @JsonProperty("pieces1")
    private List<Piece> pieces1;
    @NotNull
    @Valid
    @JsonProperty("pieces2")
    private List<Piece> pieces2;
    @NotNull
    @Valid
    @JsonProperty("pieceToMove")
    private Piece pieceToMove;
    @NotNull
    @JsonProperty("x")
    private int x;
    @NotNull
    @JsonProperty("x")
    private int y;

    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
    @JsonProperty("board")
    public Board getBoard() {
        return board;
    }
    @JsonProperty("board")
    public void setBoard(Board board) {
        this.board = board;
    }
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
    }
    @JsonProperty("y")
    public int getY() {
        return y;
    }
    @JsonProperty("y")
    public void setY(int y) {
        this.y = y;
    }
    @JsonProperty("x")
    public int getX() {
        return x;
    }
    @JsonProperty("x")
    public void setX(int x) {
        this.x = x;
    }
    @JsonProperty("pieceToMove")
    public Piece getPieceToMove() {
        return pieceToMove;
    }
    @JsonProperty("pieceToMove")
    public void setPieceToMove(Piece pieceToMove) {
        this.pieceToMove = pieceToMove;
    }
}
