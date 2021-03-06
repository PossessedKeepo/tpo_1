package edu.tpo.l1.p3;

public class PersonBuilder {

    protected Person person;

    public PersonBuilder() {
        person = new Person();
    }

    public Person build() {
        return person;
    }

    public PersonBuilder setName(String name) {
        if (name == null) throw new NullPointerException("String can not be null");
        
        this.person.setName(name);

        return this;
    }

    public PersonBuilder addState(State state, Action action, Object object) {
        if (state == null) throw new NullPointerException("State can not be null");
        if (action == null) throw new NullPointerException("Action can not be null");

        this.person.getStates().add(new DetailedState(state, action, object));

        return this;
    }

    public PersonBuilder addState(State state) {
        return this.addState(state, Action.NONE, null);
    }

    public PersonBuilder addState(DetailedState state) {
        return this.addState(state.getState(), state.getCauseAction(), state.getCauseObject());
    }

    public PersonBuilder addBodyPart(Person.BodyPart part) {
        if (part == null) throw new NullPointerException("Person.BodyPart can not be null");

        this.person.getBodyPartList().add(part);

        return this;
    }

}
