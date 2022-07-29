package com.example.maru.service;


import com.example.maru.R;
import com.example.maru.model.Meeting;
import com.example.maru.model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
        new Meeting( "Réunion A", Calendar.getInstance().getTime(), Room.MARIO, getFakeEmails(), R.color.black),
        new Meeting( "Réunion B", Calendar.getInstance().getTime(), Room.YOSHI, getFakeEmails(), R.color.purple_200),
        new Meeting( "Réunion C", Calendar.getInstance().getTime(), Room.PEACH, getFakeEmails(), R.color.teal_200),
        new Meeting( "Réunion D", Calendar.getInstance().getTime(), Room.LUIGI, getFakeEmails(), R.color.purple_200),
        new Meeting( "Réunion E", Calendar.getInstance().getTime(), Room.TOAD, getFakeEmails(), R.color.teal_200),
        new Meeting( "Réunion F", Calendar.getInstance().getTime(), Room.MARIO, getFakeEmails(), R.color.black),
        new Meeting( "Réunion G", Calendar.getInstance().getTime(), Room.LUIGI, getFakeEmails(), R.color.black),
        new Meeting( "Réunion H", Calendar.getInstance().getTime(), Room.YOSHI, getFakeEmails(), R.color.purple_200)

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
