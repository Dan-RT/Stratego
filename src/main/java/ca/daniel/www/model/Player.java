package ca.daniel.www.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"_id", "name", "team"})
@Document(collection = "player")
public class Player extends JacksonObject {
    @Id
    @JsonProperty("_id")
    private ObjectId _id;
    @NotNull
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotNull
    @JsonProperty("team")
    private int team;

    public Player() {
        super();
    }

    @JsonProperty("_id")
    public ObjectId get_id() {
        return _id;
    }
    @JsonProperty("_id")
    public void set_id(ObjectId _id) {
        this._id = _id;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("team")
    public int getTeam() {
        return team;
    }
    @JsonProperty("team")
    public void setTeam(int team) {
        this.team = team;
    }
}
