package com.example.maru;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.DummyMeetingGenerator;
import com.example.maru.service.MeetingApiService;

import java.util.List;

/**
 * Unit Test on Maréu service
 */
public class MareuServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void generateListMeetingWithSuccess() {
        List<Meeting> lMeetings =  service.getMeetings();
        int listSize = lMeetings.size();
        assertEquals( 5, listSize );
    }

    @Test
    public void addMeetingWithSucess() {
        Meeting meetingToCreate = DummyMeetingGenerator.DUMMY_MEETINGS.get(0);
        service.createMeeting(meetingToCreate);
        assertTrue(service.getMeetings().contains(meetingToCreate));
    }

    @Test
    public void removeMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterByRoom() {

    }

    @Test
    public void filterByDate() {

    }

    @Test
    public void checkRoomAvailability() {

    }


}