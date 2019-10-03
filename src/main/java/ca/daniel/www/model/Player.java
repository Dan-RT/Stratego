package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Player extends JacksonObject {
    @JsonProperty("_id")
    private ObjectId _id;
    @NotNull
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotNull
    @JsonProperty("team")
    private int team;
}
