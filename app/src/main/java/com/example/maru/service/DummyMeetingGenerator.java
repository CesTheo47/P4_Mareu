package com.example.maru.service;


import android.graphics.Color;

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
        new Meeting( "Réunion A", Calendar.getInstance().getTime(), Room.PARIS, getFakeEmails(), Color.parseColor("#FF0000")),
        new Meeting( "Réunion B", Calendar.getInstance().getTime(), Room.MADRID, getFakeEmails(), Color.parseColor("#F7F074")),
        new Meeting( "Réunion C", Calendar.getInstance().getTime(), Room.ROME, getFakeEmails(), Color.parseColor("#81f774")),
        new Meeting( "Réunion D", Calendar.getInstance().getTime(), Room.PARIS, getFakeEmails(), Color.parseColor("#3e38ed")),
        new Meeting( "Réunion E", Calendar.getInstance().getTime(), Room.SYDNEY, getFakeEmails(), Color.parseColor("#ea38ed"))
        //new Meeting( "Réunion F", Calendar.getInstance().getTime(), Room.PEKIN, getFakeEmails(), Color.parseColor("#F7F074")),
        //new Meeting( "Réunion G", Calendar.getInstance().getTime(), Room.NEW_YORK, getFakeEmails(), Color.parseColor("#fcc244")),
        //new Meeting( "Réunion H", Calendar.getInstance().getTime(), Room.LONDRES, getFakeEmails(), Color.parseColor("#81f774"))

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
