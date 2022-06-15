package com.example.maru.model;

public enum Room {
    PEACH("Peach"),
    MARIO("Mario"),
    YOSHI("Yoshi"),
    TOAD("Toad"),
    LUIGI("Luigi");

    private final String name;

    Room(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
