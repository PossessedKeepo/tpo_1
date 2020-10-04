package edu.tpo.l1.p3;

public enum Action {
    NONE(""),
    PICKING("ковырянием"),
    SEEN("видя");

    private final String name;

    Action(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
