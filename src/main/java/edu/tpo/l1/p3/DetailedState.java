package edu.tpo.l1.p3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class DetailedState {
    private State state;
    private Action causeAction;
    private Object causeObject;

    private String presentation;

    public DetailedState(State state, Action action, Object object) {
        this.state = state;
        causeAction = action;
        causeObject = object;

        changePresentation();
    }

    public DetailedState() {
        this(State.NONE, Action.NONE, null);
    }

    @Override
    public String toString() {
        return presentation;
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

    private void changePresentation() {
        StringBuilder output = new StringBuilder();

        output.append(state);

        if (causeAction != Action.NONE) {
            output.append(" ").append(causeAction);
        }

        if (causeObject != null) {
            if (causeObject.getClass().equals(Person.class)) {
                output.append(" ").append(causeObject).append(((Person) causeObject).getStatesPresentation());
            } else {
                output.append(" ").append(causeObject);
            }
        }

        presentation = output.toString();
    }
}
