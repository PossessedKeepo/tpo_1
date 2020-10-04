package edu.tpo.l1.p3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class DetailedState {
    State state;
    Action causeAction;
    Object causeObject;

    public DetailedState(State state, Action action, Object object) {
        this.state = state;
        causeAction = action;
        causeObject = object;
    }

    public DetailedState() {
        state = State.NONE;
        causeAction = Action.NONE;
        causeObject = null;
    }

    @Override
    public String toString() {
        if (causeObject != null && causeObject.getClass().equals(Person.class)) {
            return state + " " + causeAction + " " + ((Person)causeObject) + ((Person) causeObject).getStatesPresentation();
        }

        return state + " " + causeAction + " " + (causeObject == null ? "" : causeObject);
    }

    public State getState() {
        return state;
    }

    public Action getCauseAction() {
        return causeAction;
    }

    public Object getCauseObject() {
        return causeObject;
    }
}
