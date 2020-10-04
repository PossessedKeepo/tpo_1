package edu.tpo.l1;

import edu.tpo.l1.p3.*;

public class Main {
    public static void main(String[] args) {
        Person.BodyPart arthurJaw = new Person.BodyPart("челюсть");

        Person arthur = new PersonBuilder()
                .setName("Артур")
                .addBodyPart(arthurJaw)
                .addState(State.NERVOUS)
                .build();

        Stuff chair = new Stuff("кресло");
        Stuff console = new Stuff("пульт управления");
        Stuff things = new Stuff("количество вещей");

        Person.BodyPart rightHead = new Person.BodyPart("правая голова");
        Person.BodyPart leftHead = new Person.BodyPart("левая голова");
        Person.BodyPart teeth = new Person.BodyPart(rightHead, "зубы");


        DetailedState humanHeadState = new DetailedState(State.PICKING, Action.NONE, teeth);
        rightHead.setState(new DetailedState(State.BUSY, Action.PICKING, null));

        Person human = new PersonBuilder()
                .setName("человек")
                .addBodyPart(rightHead)
                .addBodyPart(leftHead)
                .addBodyPart(teeth)
                .addState(State.RELAXED, Action.NONE, chair)
                .addState(State.PUT_LEGS, Action.NONE, console)
                .addState(humanHeadState)
                .build();


        arthur.enter(Direction.TOWARDS);
        arthur.addState(State.STUNNED, Action.SEEN, human);

        leftHead.changeState(State.WIDE_SMILE);
        leftHead.changeState(State.CASUALLY_SMILE);

        arthur.changeState(new DetailedState(State.CANT_BELIEVE_EYES, Action.SEEN, things));

        things.changeState(new DetailedState(State.GROWING, Action.NONE, null));

        arthurJaw.changeState(State.DROOPED);
    }
}
