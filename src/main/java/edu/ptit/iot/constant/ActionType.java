package edu.ptit.iot.constant;

/**
 * Warning: only add new value after all existed value to avoid bug when persisting to database
 */
public enum ActionType {
    TURN_ON ("Turn on"),
    TURN_OFF ("Turn off");

    private final String label;

    ActionType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
