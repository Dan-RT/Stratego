package ca.daniel.www.model.customEnum;

public enum Movement {
        NORMAL("NORMAL"), JUMP("JUMP"), IDLE("IDLE");

    private String type;

    Movement(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
