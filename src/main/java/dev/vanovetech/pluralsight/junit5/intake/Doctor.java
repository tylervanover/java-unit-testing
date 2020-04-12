package dev.vanovetech.pluralsight.junit5.intake;

public enum Doctor {
    avery("Ralpha Avery")
    , johnson("Beth Johnson")
    , murphy("Pat Murphy");

    private String name;
    Doctor(String name) { this.name = name; }
    public String getName() { return name; }
}
