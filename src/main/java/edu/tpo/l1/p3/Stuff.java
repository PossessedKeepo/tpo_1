package edu.tpo.l1.p3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Stuff implements Witnessable, Loggable {
    private String name;
    private int amount = 1;
    private DetailedState state;

    private StringBuilder logger = new StringBuilder();

    public Stuff(String name) {
        this.name = name;
    }

    public void changeState(State state) {
        if (state == null) throw new NullPointerException("State can not be null");

        changeState(new DetailedState(state, Action.NONE, null));
    }

    @Override
    public void changeState(DetailedState state) {
        if (state == null) throw new NullPointerException("State can not be null");

        this.state = state;

        logger.append(this).append(" ").append(state);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getLog() {
        return logger.toString().trim();
    }
}
