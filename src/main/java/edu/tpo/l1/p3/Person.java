package edu.tpo.l1.p3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Person implements Witnessable {

    private String name = "";

    private List<DetailedState> states = new ArrayList<>();
    private List<BodyPart> bodyPartList = new ArrayList<>();

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class BodyPart {
        private BodyPart parent = null;
        private String name = "";
        private DetailedState state = new DetailedState();

        public BodyPart(String name) {
            this.name = name;
        }

        public BodyPart(BodyPart parent, String name) {
            this.parent = parent;
            this.name = name;
        }

        public void changeState(DetailedState state) {
            this.state = state;

            System.out.println(this);
        }

        public void changeState(State state) {
            changeState(new DetailedState(state, Action.NONE, null));
        }

        @Override
        public String toString() {
            return name + " " + state + (parent != null ? " на " + parent : "");
        }

        public void setState(DetailedState detailedState) {
        }
    }

    private void setBodyPartList(List<BodyPart> bodyPartList) {}
    private void setStates(List<DetailedState> stateList) {}

    public void enter(Direction direction) {
        if (direction == null) throw new NullPointerException("Direction can not be null");

        System.out.print(this + " ");
        for (DetailedState state : states) {
            System.out.print(state + " ");
        };
        System.out.println( "вошел " + direction);
    }

    public String getStatesPresentation() {
        StringBuilder res = new StringBuilder();
        states.forEach(s -> {
            res.append(", ");
            res.append(s);
        });

        return res.toString();
    }

    public void addState(State state, Action action, Object object) {
        if (state == null) throw new NullPointerException("State can not be null");
        if (action == null) throw new NullPointerException("Action can not be null");

        DetailedState detailedState = new DetailedState(state, action, object);
        System.out.println(name + " " + detailedState);

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
}
