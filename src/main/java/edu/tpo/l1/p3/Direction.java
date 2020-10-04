package edu.tpo.l1.p3;

public enum Direction {
    TOWARDS("следом");

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
