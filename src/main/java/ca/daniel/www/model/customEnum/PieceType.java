package ca.daniel.www.model.customEnum;


public enum PieceType {
    MARSHAL(10), GENERAL(9), COLONEL(8), MAJOR(7), CAPTAIN(6), LIEUTENANT(5),
    SERGEANT(4), MINER(3), SCOUT(2), SPY(1), BOMB(11), FLAG(0), NONE(-1), LAKE(-2);

    private int rank;

    PieceType(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
