package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"board"})
public class Board extends JacksonObject {
    @NotNull
    @JsonProperty("board")
    private Piece[][] board;

    public Board() {

    }

    @JsonProperty("board")
    public Piece[][] getBoard() {
        return board;
    }
    @JsonProperty("board")
    public void setBoard(Piece[][] board) {
        this.board = board;
    }
}
