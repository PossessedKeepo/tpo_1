package edu.tpo.l1.p3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    private Person person;
    private String actualLog;
    private String expectedLog;

    @Before
    public void setUp() throws Exception {
        person = new Person();
        person.setName("PersonName");
    }

    @Test(expected = NullPointerException.class)
    public void enter_CheckDirectionIsNull_ShouldThrowNPE() {
        person.enter(null);
    }

    @Test
    public void enter_CheckDirectionIsGiven_ShouldProduceCorrectLog() {
        person.enter(Direction.TOWARDS);

        actualLog = person.getLog();
        expectedLog = "PersonName вошел следом";

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void addState_StunnedStateGiven_ShouldProduceCorrectLog() {
        person.addState(State.STUNNED, Action.NONE, null);

        actualLog = person.getLog();
        expectedLog = "PersonName ошеломлен";

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void addState_StunnedStateGivenWithCause_ShouldProduceCorrectLog() {
        Stuff stuff = new Stuff("STUFF");
        person.addState(State.STUNNED, Action.SEEN, stuff);

        actualLog = person.getLog();
        expectedLog = "PersonName ошеломлен видя STUFF";

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void changeState_StunnedStateGiven_ShouldRemoveOldStatesAndSetNewOne() {
        person.addState(State.BUSY, Action.NONE, null);
        person.addState(State.CANT_BELIEVE_EYES, Action.NONE, null);

        person.changeState(new DetailedState(State.STUNNED, Action.NONE, null));

        actualLog = person.getLog();
        expectedLog = "PersonName занятая\nPersonName не верит своим глазам\nPersonName ошеломлен";

        assertEquals(expectedLog, actualLog);
        assertEquals(1, person.getStates().size());
    }

    @Test
    public void BodyPartChangeState_GrowingStateGiven_ShouldProduceCorrectLog() {
        Person.BodyPart part = new Person.BodyPart("BODY_PART");

        part.changeState(State.GROWING);

        actualLog = part.getLog();
        expectedLog = "BODY_PART увеличивается";

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void BodyPartChangeState_GrowingStateGivenWithParent_ShouldProduceCorrectLog() {
        Person.BodyPart partParent = new Person.BodyPart("BODY_PART_PARENT");
        Person.BodyPart part = new Person.BodyPart(partParent, "BODY_PART");

        part.changeState(State.GROWING);

        actualLog = part.getLog();
        expectedLog = "BODY_PART увеличивается на BODY_PART_PARENT";

        assertEquals(expectedLog, actualLog);
    }
}