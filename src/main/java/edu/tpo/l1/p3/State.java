package edu.tpo.l1.p3;

public enum State {
    NONE(""),
    STUNNED("ошеломлен"),
    RELAXED("развалившегося на"),
    PUT_LEGS("положившего ноги на"),
    PICKING("ковыряющего"),
    BUSY("занятая"),
    WIDE_SMILE("широко улыбается"),
    CASUALLY_SMILE("непринужденно улыбается"),
    CANT_BELIEVE_EYES("не верит своим глазам"),
    DROOPED("отвисла"),
    GROWING("увеличивается"),
    NERVOUS("нервничая");

    private final String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
