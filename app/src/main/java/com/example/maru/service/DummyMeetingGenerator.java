package com.example.maru.service;


import com.example.maru.model.Meeting;
import com.example.maru.model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
        new Meeting(1, "Réunion A", Calendar.getInstance().getTime(), Room.MARIO, getFakeEmails()),
        new Meeting(2, "Réunion B", Calendar.getInstance().getTime(), Room.YOSHI, getFakeEmails()),
        new Meeting(3, "Réunion C", Calendar.getInstance().getTime(), Room.PEACH, getFakeEmails()),
        new Meeting(4, "Réunion D", Calendar.getInstance().getTime(), Room.LUIGI, getFakeEmails()),
        new Meeting(5, "Réunion E", Calendar.getInstance().getTime(), Room.TOAD, getFakeEmails()),
        new Meeting(6, "Réunion F", Calendar.getInstance().getTime(), Room.MARIO, getFakeEmails()),
        new Meeting(7, "Réunion G", Calendar.getInstance().getTime(), Room.LUIGI, getFakeEmails()),
        new Meeting(8, "Réunion H", Calendar.getInstance().getTime(), Room.YOSHI, getFakeEmails())

    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    private static List<String> getFakeEmails() {
        List<String> emails = new ArrayList<>();
        emails.add("test@test.com");
        emails.add("marius@test.com");
        emails.add("orange@test.com");
        emails.add("cerise@test.com");
        emails.add("dog@test.com");
        return emails;
    }


}
