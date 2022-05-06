package com.example.maru.model;

public class Meeting {

    private long id;

    /** Name */
    private String name;

    /** Date */
    private long date;

    /** Room */
    private String room;

    /** Email */
    private String email;

    /** Color */


    public Meeting(long id, String name, long date, String room, String email){

        this.id = id;
        this.name = name;
        this.date = date;
        this.room = room;
        this.email = email;
    }

}
