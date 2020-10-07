package edu.tpo.l1.p3;

import org.junit.Test;

import static org.junit.Assert.*;

public class StuffTest {

    @Test
    public void changeState_StunnedStateGiven_ShouldProduceCorrectLog() {
        Stuff stuff = new Stuff("STUFF");
        stuff.changeState(State.STUNNED);

        String actualLog = stuff.getLog();
        String expectedLog = "STUFF ошеломлен";

        assertEquals(expectedLog, actualLog);
    }

}