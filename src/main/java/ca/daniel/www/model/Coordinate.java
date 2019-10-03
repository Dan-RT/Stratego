package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"x", "y"})

public class Coordinate extends JacksonObject {
    @NotNull
    @JsonProperty("x")
    private int x;
    @NotNull
    @JsonProperty("y")
    private int y;

    public Coordinate() {

    }

    public Coordinate(int x, int y) {
        this.x = x;
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
    @JsonProperty("y")
    public int getY() {
        return y;
    }
    @JsonProperty("y")
    public void setY(int y) {
        this.y = y;
    }
}
