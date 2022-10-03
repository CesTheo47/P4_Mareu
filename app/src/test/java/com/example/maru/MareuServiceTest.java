package com.example.maru;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.model.Room;
import com.example.maru.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Unit Test on Maréu service
 */
@RunWith(JUnit4.class)
public class MareuServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
//        Color color = mock(Color.class);
//        when(color.)
    }

    @Test
    public void generateListMeetingWithSuccess() {
        List<Meeting> lMeetings = service.getMeetings();
        int listSize = lMeetings.size();
        assertEquals(5, listSize);
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToCreate = new Meeting("Réunion test", Calendar.getInstance().getTime(), Room.PEKIN, new ArrayList<>(), -1500);
        service.createMeeting(meetingToCreate);
        assertTrue(service.getMeetings().contains(meetingToCreate));
    }

    @Test
    public void addMeetingWithErrorSameRoom() {
        Meeting meetingToCreate = new Meeting("Réunion test", Calendar.getInstance().getTime(), Room.ROME, new ArrayList<>(), -1500);
        service.createMeeting(meetingToCreate);
        assertFalse(service.getMeetings().contains(meetingToCreate));
    }

    @Test
    public void removeMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterByRoom() {
        List<Meeting> lMeetingsFiltered = service.getMeetingsByRoom(Room.PARIS);

        for (Meeting m : lMeetingsFiltered) {
            assertEquals(m.getRoom(), Room.PARIS);
        }
    }

    @Test
    public void filterByDate () {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, 20);
        c.set(Calendar.MONTH, 4);
        c.set(Calendar.YEAR, 22);

        Meeting meetingToCreate = new Meeting("Réunion test", c.getTime(), Room.ROME, new ArrayList<>(), -1500);
        service.createMeeting(meetingToCreate);

        Calendar cBegin = Calendar.getInstance();
        c.set(Calendar.DATE, 19);
        c.set(Calendar.MONTH, 4);
        c.set(Calendar.YEAR, 22);

        Calendar cEnd = Calendar.getInstance();
        c.set(Calendar.DATE, 21);
        c.set(Calendar.MONTH, 4);
        c.set(Calendar.YEAR, 22);

        List<Meeting> lMeetingsFilteredDate = service.getMeetingsByDates(cBegin.getTime(), cEnd.getTime());

        assertEquals(1, lMeetingsFilteredDate.size());
        assertTrue(lMeetingsFilteredDate.contains(meetingToCreate));
    }

}