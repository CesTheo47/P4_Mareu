package com.example.maru.model;

public enum Room {
    PARIS("Paris"),
    LONDRES("Londre"),
    SYDNEY("Sydney"),
    MADRID("Madrid"),
    ROME("Rome"),
    RIO("Rio"),
    PEKIN("Pekin"),
    BERLIN("Berlin"),
    LOS_ANGELES("Los Angeles"),
    NEW_YORK("New York");

    private final String name;

    Room(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
