package ca.daniel.www.model;

import ca.daniel.www.exception.SquareNotEmptyException;
import ca.daniel.www.model.customEnum.PieceType;
import ca.daniel.www.service.TurnService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
