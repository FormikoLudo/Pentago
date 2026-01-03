package fr.formiko.pentago.model;

public enum State {
    EMPTY("_"), WHITE("X"), BLACK("O");

    private String printValue;

    private State(String printValue) { this.printValue = printValue; }

    @Override
    public String toString() { return printValue; }
}
