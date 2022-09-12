package com.example.maru;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import com.example.maru.model.Meeting;
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
        //assertEquals( 5, listSize );
    }

    @Test
    public void getMeetingWithSuccess() {

    }

    @Test
    public void createMeetingWithSuccess() {

    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterByRoomWithSuccess() {

    }

    @Test
    public void filterByDateWithSuccess() {

    }

    @Test
    public void checkRoomAvailability() {

    }


}