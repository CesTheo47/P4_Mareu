package com.example.maru.model;

import androidx.annotation.ColorRes;

import java.util.Date;
import java.util.List;

public class Meeting {

    private long id;

    /** Name */
    private String name;

    /** Date */
    private Date date;

    /** Room */
    private Room room;

    /** Email */
    private List<String> email;

    /** Color */
    @ColorRes
    private int color;

    public Meeting(long id, String name, Date date, Room room, List<String> email){

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

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public List<String> getEmail() { return email; }

    public void setEmail(List<String> email) { this.email = email; }


}
