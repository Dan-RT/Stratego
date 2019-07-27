package ca.daniel.www.model;

import org.bson.types.ObjectId;

public class Player extends JacksonObject {
    private ObjectId _id;
    private String name;
    private int team;
}
