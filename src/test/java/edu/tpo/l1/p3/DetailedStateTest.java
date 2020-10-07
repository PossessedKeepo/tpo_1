package edu.tpo.l1.p3;

import org.junit.Test;

import static org.junit.Assert.*;

public class DetailedStateTest {

    @Test
    public void toString_StunnedStageGiven_ShouldProduceCorrectPresentation() {
        DetailedState state = new DetailedState(State.STUNNED, Action.NONE, null);

        String expectedPresentation = State.STUNNED.toString();
        String actualPresentation = state.toString();

        assertEquals(expectedPresentation, actualPresentation);
    }

    @Test
    public void toString_StunnedStageGivenWithCause_ShouldProduceCorrectPresentation() {
        DetailedState state = new DetailedState(State.STUNNED, Action.SEEN, "STUFF");

        String expectedPresentation = "ошеломлен видя STUFF";
        String actualPresentation = state.toString();

        assertEquals(expectedPresentation, actualPresentation);
    }

}