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

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public long getDate() { return date; }

    public void setDate(long date) { this.date = date; }

    public String getRoom() { return room; }

    public void setRoom(String room) { this.room = room; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


}
