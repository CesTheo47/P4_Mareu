package com.example.maru.service;

import com.example.maru.model.Meeting;
import com.example.maru.model.Room;

import java.util.Date;
import java.util.List;

public interface MeetingApiService {

    //Get all my meeting
    List<Meeting> getMeetings();
    List<Meeting> getMeetingsByDates(Date startDate, Date endDate);
    List<Meeting> getMeetingsByRoom(Room room);

    Meeting getMeeting(Long id);

    // Create meeting
    void createMeeting(Meeting meeting);

    // Delete meeting
    void deleteMeeting(Meeting meeting);
}
