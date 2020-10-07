package edu.tpo.l1.p3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Person implements Witnessable, Loggable {

    private StringBuilder logger = new StringBuilder();

    private String name = "";

    private List<DetailedState> states = new ArrayList<>();
    private List<BodyPart> bodyPartList = new ArrayList<>();

    @Override
    public String getLog() {
        return logger.toString().trim();
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class BodyPart implements Loggable {
        private BodyPart parent = null;
        private String name = "";
        private DetailedState state = new DetailedState();

        private StringBuilder logger = new StringBuilder();

        public BodyPart(String name) {
            this.name = name;
        }

        public BodyPart(BodyPart parent, String name) {
            this.parent = parent;
            this.name = name;
        }

        public void changeState(DetailedState state) {
            this.state = state;

            logger.append(this);
        }

        public void changeState(State state) {
            changeState(new DetailedState(state, Action.NONE, null));
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();

            output.append(name);

            if (state != null) {
                output.append(" ").append(state);
            }

            if (parent != null) {
                output.append(" на ").append(parent);
            } else {
                output.append("\n");
            }

            return output.toString();
        }

        public void setState(DetailedState detailedState) {
        }

        @Override
        public String getLog() {
            return logger.toString().trim();
        }
    }

    public void enter(Direction direction) {
        if (direction == null) throw new NullPointerException("Direction can not be null");

        logger.append(this).append(" ");
        for (DetailedState state : states) {
            logger.append(state).append(" ");
        };
        logger.append("вошел ").append(direction).append("\n");
    }

    public void addState(State state, Action action, Object object) {
        if (state == null) throw new NullPointerException("State can not be null");
        if (action == null) throw new NullPointerException("Action can not be null");

        DetailedState detailedState = new DetailedState(state, action, object);
        logger.append(name).append(" ").append(detailedState).append("\n");

        this.states.add(detailedState);
    }

    public void addState(DetailedState state) {
        if (state == null) throw new NullPointerException("DetailedState can not be null");

        addState(state.getState(), state.getCauseAction(), state.getCauseObject());
    }

    @Override
    public void changeState(DetailedState state) {
        if (state == null) throw new NullPointerException("DetailedState can not be null");

        this.states = new ArrayList<>();

        addState(state);
    }

    @Override
    public String toString() {
        return name;
    }

    protected String getStatesPresentation() {
        StringBuilder res = new StringBuilder();
        states.forEach(s -> {
            res.append(", ");
            res.append(s);
        });

        return res.toString();
    }
}
