package com.example.maru.service;


import com.example.maru.model.Meeting;
import com.example.maru.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Réunion A", Calendar.getInstance().getTime(), Room.PARIS, getFakeEmails(), -10177034),
            new Meeting("Réunion B", Calendar.getInstance().getTime(), Room.MADRID, getFakeEmails(), -5319295),
            new Meeting("Réunion C", Calendar.getInstance().getTime(), Room.ROME, getFakeEmails(), -1500),
            new Meeting("Réunion D", Calendar.getInstance().getTime(), Room.LONDRES, getFakeEmails(), -225550),
            new Meeting("Réunion E", Calendar.getInstance().getTime(), Room.SYDNEY, getFakeEmails(), -3722)


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
