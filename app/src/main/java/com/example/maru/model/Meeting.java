package com.example.maru.model;

import java.util.Date;
import java.util.List;

public class Meeting {


    /** Name */
    private String name;

    /** Date */
    private Date date;

    /** Room */
    private Room room;

    /** Email */
    private List<String> emailList;

    /** Color */
    private int color;

    public Meeting(String name, Date date, Room room, List<String> emailList, int color){

        this.name = name;
        this.date = date;
        this.room = room;
        this.emailList = emailList;
        this.color = color;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
