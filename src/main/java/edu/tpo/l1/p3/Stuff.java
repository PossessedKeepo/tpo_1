package edu.tpo.l1.p3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Stuff implements Witnessable {
    private String name;
    private int amount = 1;
    private DetailedState state;

    public Stuff(String name) {
        this.name = name;
    }

    @Override
    public void changeState(DetailedState state) {
        if (state == null) throw new NullPointerException("State can not be null");

        this.state = state;

        System.out.println(this + " " + state);
    }

    @Override
    public String toString() {
        return name;
    }
}
