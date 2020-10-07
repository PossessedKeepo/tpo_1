package edu.tpo.l1.p3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonBuilderTest {

    private PersonBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new PersonBuilder();
    }

    @Test
    public void addState_AddStunnedState_ShouldProduceCorrectLog() {
        builder.addState(State.STUNNED);

        String actualLog = builder.getLog();
        String expectedLog = "ошеломлен";

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void addState_AddNameAndStunnedState_ShouldProduceCorrectLog() {
        builder.setName("PERSON");
        builder.addState(State.STUNNED);

        String actualLog = builder.getLog();
        String expectedLog = "PERSON ошеломлен";

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void addBodyPart_AddPart_ShouldProduceCorrectLog() {
        Person.BodyPart part = new Person.BodyPart("BODY_PART");
        builder.addBodyPart(part);

        Person person = builder.build();

        assertEquals(1, person.getBodyPartList().size());
        assertEquals(part, person.getBodyPartList().get(0));
    }

}